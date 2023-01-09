package com.narvatov.planthelper.data.repository

import android.app.Activity
import com.android.billingclient.api.*
import com.android.billingclient.api.BillingClient.BillingResponseCode
import com.android.billingclient.api.BillingClient.ProductType
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.datasource.local.dao.BillingDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.*
import com.narvatov.planthelper.models.data.local.BillingSubscription
import com.narvatov.planthelper.models.ui.purchase.BillingState
import com.narvatov.planthelper.utils.logSeparator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.annotation.Single


@Single
class BillingRepository(
    private val billingDao: BillingDao,
): Repository() {

    private val _billingProductsFlow = MutableSharedFlow<BillingState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val billingProductsFlow = _billingProductsFlow.asSharedFlow()

    fun flowPurchasedProducts() = billingDao.flowAll()


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
            logBilling("Billing is already connected. Reusing current connection...")
        }

        logBilling("Connecting...")

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

                    _billingProductsFlow.tryEmit(BillingState.Error)
                }
            },
            onDisconnected = {
                logBilling("Billing has been disconnected")

                _billingProductsFlow.tryEmit(BillingState.Error)
            }
        )
    }

    private fun processPurchases() {
        if (productDetailsList.isNotEmpty()) {
            logBilling("Purchases has already been queried. Reusing loaded products.")

            _billingProductsFlow.tryEmit(BillingState.Success(productDetailsList))

            return
        }

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
        repositoryScope.launchCatching {
            purchasedProductsList.clear()
            billingDao.clear()
        }

        purchases.map(::handlePurchase)

        repositoryScope.launchCatching {
            val billingSubscription = purchasedProductsList.map {
                BillingSubscription(it.products.first())
            }
            billingDao.insert(billingSubscription)
        }
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