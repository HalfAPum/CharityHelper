package com.narvatov.planthelper.data.repository

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.ProductDetails
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.*
import com.narvatov.planthelper.models.ui.purchase.BillingState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.annotation.Single

@Single
class BillingRepository: Repository() {

    private val _billingProductsFlow = MutableSharedFlow<BillingState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val billingProductsFlow = _billingProductsFlow.asSharedFlow()


    private val productDetailsList = mutableListOf<ProductDetails>()

    private val billingClient by lazy {
        BillingClient.newBuilder(applicationContext).run {
            setListener(purchasesUpdatedListener)
            enablePendingPurchases()
            build()
        }
    }


    fun connectToBilling() {
        logBilling("Try to connect to billing")

        if (billingClient.isConnected) {
            logBilling("Billing is already connected")
            return
        }

        _billingProductsFlow.tryEmit(BillingState.Loading)

        billingClient.startConnection(
            onSetupFinished = { billingResult ->
                // TODO ADD ANALYTICS
                if (billingResult.responseCode == BillingResponseCode.OK) {
                    logBilling("Connected to billing successfully")

                    processPurchases()

                    _billingProductsFlow.tryEmit(BillingState.Success(productDetailsList))
                } else {
                    logBilling("Connected to billing unsuccessfully. $billingResult")
                }
            },
            onDisconnected = {
                logBilling("Billing has been disconnected")

                _billingProductsFlow.tryEmit(BillingState.Error)
            }
        )
    }

    private fun processPurchases() {
        if (productDetailsList.isNotEmpty()) return

        billingClient.queryProductDetailsAsync(billingProductsDetailsParams) { _, queriedProductDetailsList ->
            logBilling("Queried products ${queriedProductDetailsList.map { it.name }}")

            productDetailsList.addAll(queriedProductDetailsList)
        }
    }

}