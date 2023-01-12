package com.narvatov.planthelper.data.utils

import com.android.billingclient.api.*
import com.narvatov.planthelper.models.data.local.BillingSubscription
import com.narvatov.planthelper.models.ui.purchase.SuccessfulPurchaseUiState
import timber.log.Timber

private fun subscriptionProduct(productId: String): QueryProductDetailsParams.Product {
    return QueryProductDetailsParams.Product.newBuilder().run {
        setProductId(productId)
        setProductType(BillingClient.ProductType.SUBS)
        build()
    }
}

private val subscriptionProductList = productIdList.map {
    subscriptionProduct(it)
}

val billingProductsDetailsParams = QueryProductDetailsParams.newBuilder().run {
    setProductList(subscriptionProductList)
    build()
}


fun BillingClient.startConnection(
    onSetupFinished: (BillingResult) -> Unit,
    onDisconnected: () -> Unit,
) {
    startConnection(object : BillingClientStateListener {
        override fun onBillingServiceDisconnected() {
            onDisconnected()
        }

        override fun onBillingSetupFinished(billingResult: BillingResult) {
            onSetupFinished(billingResult)
        }
    })
}

fun logBilling(message: String) {
    Timber.d("Billing | $message")
}

val BillingClient.isConnected: Boolean
    get() = connectionState == BillingClient.ConnectionState.CONNECTED

val ProductDetails.billingFlowParams: BillingFlowParams
    get() {
        val productDetailsParamsList = listOf(
            BillingFlowParams.ProductDetailsParams.newBuilder()
                .setProductDetails(this)
                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
                // for a list of offers that are available to the user
                .setOfferToken(this.subscriptionOfferDetails!!.first().offerToken)
                .build()
        )


        return BillingFlowParams.newBuilder()
            .setProductDetailsParamsList(productDetailsParamsList)
            .build()
    }

val acknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener { billingResult ->
    logBilling("Acknowledge result $billingResult")
}

val Purchase.requireAcknowledge: Boolean
    get() = purchaseState == Purchase.PurchaseState.PURCHASED && !isAcknowledged


fun BillingClient.acknowledgePurchase(purchase: Purchase) {
    val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
        .setPurchaseToken(purchase.purchaseToken)
        .build()

    acknowledgePurchase(
        acknowledgePurchaseParams,
        acknowledgePurchaseResponseListener,
    )
}

fun List<Purchase>.toBillingSubscriptions() = map {
    BillingSubscription(it.products.first())
}

fun List<ProductDetails>.toSubscriptionDetailsList() = mapNotNull {
    val subscriptionDetails = subscriptionIdsToSubscriptionDetails[it.productId]
    subscriptionDetails?.productDetails = it
    subscriptionDetails
}.sortedBy {
    productIdList.indexOf(it.productDetails?.productId)
}
