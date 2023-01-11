package com.narvatov.planthelper.data.repository.billing

import android.app.Activity
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryPurchasesParams
import com.narvatov.planthelper.data.datasource.local.dao.BillingDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.*
import com.narvatov.planthelper.models.ui.purchase.BillingState
import com.narvatov.planthelper.utils.logSeparator
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
class BillingPurchaseProcessor(
    private val billingClient: BillingClient,
    private val billingDao: BillingDao,
): Repository() {

    init {
        BillingPurchaseListener.successfulPurchasesUpdateFlow
            .onEach(::handlePurchases)
            .launchIn(repositoryScope)
    }


    fun processPurchases(billingConnectionFlow: MutableSharedFlow<BillingState>) {
        billingClient.queryProductDetailsAsync(billingProductsDetailsParams) { _, queriedProductDetailsList ->
            logBilling("Queried products ${queriedProductDetailsList.map { it.name }}")

            //todo refactor
            val sortedProductDetailsList = queriedProductDetailsList.sortedBy {
                productIdList.indexOf(it.productId)
            }.mapNotNull {
                val subscriptionDetails = subscriptionIdsToSubscriptionDetails[it.productId]
                subscriptionDetails?.productDetails = it
                subscriptionDetails
            }

            billingConnectionFlow.tryEmit(BillingState.Success(sortedProductDetailsList))
        }
    }


    fun processUnhandledPurchases() {
        logBilling("Process unhandled purchases")

        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.SUBS)
                .build()
        ) { _, purchases -> handlePurchases(purchases) }
    }

    private fun handlePurchases(purchases: List<Purchase>) {
        launchCatching { billingDao.clear() }

        val purchasedProductList = purchases.mapNotNull(::handlePurchase)

        launchCatching {
            val billingSubscriptions = purchasedProductList.toBillingSubscriptions()
            billingDao.insert(billingSubscriptions)
        }
    }

    private fun handlePurchase(purchase: Purchase): Purchase? {
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
            return purchase
        }

        return null
    }

}