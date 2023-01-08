package com.narvatov.planthelper.data.repository

import android.app.Activity
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.BillingClient.ProductType
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.*
import com.narvatov.planthelper.models.ui.purchase.BillingState
import com.narvatov.planthelper.utils.logSeparator
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

    private val _purchasedProductsFlow = MutableSharedFlow<List<Purchase>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val purchasedProductsFlow = _purchasedProductsFlow.asSharedFlow()


    private val productDetailsList = mutableListOf<ProductDetails>()

    private val purchasedProductsList = mutableListOf<Purchase>()

    private val purchasesUpdatedListener = BillingPurchaseListener(::handlePurchases)

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

                    processUnhandledPurchases()

                    processPurchases()
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

            val sortedProductDetailsList = queriedProductDetailsList.sortedBy {
                productIdList.indexOf(it.productId)
            }

            productDetailsList.addAll(sortedProductDetailsList)

            _billingProductsFlow.tryEmit(BillingState.Success(productDetailsList))
        }
    }

    private fun processUnhandledPurchases() {
        logBilling("Process unhandled purchases")

        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(ProductType.SUBS)
                .build()
        ) { _, purchases -> handlePurchases(purchases) }
    }


    fun launchBillingFlow(activity: Activity, billingFlowParams: BillingFlowParams) {
        logBilling("Launch purchase flow")

        billingClient.launchBillingFlow(activity, billingFlowParams)
    }

    private fun handlePurchases(purchases: List<Purchase>) {
        purchases.map(::handlePurchase)

        _purchasedProductsFlow.tryEmit(purchasedProductsList)
    }

    private fun handlePurchase(purchase: Purchase) {
        logSeparator()

        logBilling("Try to acknowledge purchase $purchase")

        if (purchase.requireAcknowledge) {
            logBilling("Purchase is not acknowledged. Acknowledging...")

            billingClient.acknowledgePurchase(purchase)
        } else {
            logBilling("Purchase is already acknowledged")
        }

        if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED
            || purchase.purchaseState == Purchase.PurchaseState.PENDING) {
            purchasedProductsList.add(purchase)
        }

        logSeparator()
    }

}