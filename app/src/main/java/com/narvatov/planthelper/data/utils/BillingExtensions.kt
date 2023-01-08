package com.narvatov.planthelper.data.utils

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.narvatov.planthelper.utils.logSeparator
import timber.log.Timber

// Available subscription
private const val SIMPLE_SLOT_SUBSCRIPTION = "plant_slots_subscriptions"
private const val BASE_SLOT_SUBSCRIPTION = "plant_4_slots_subscriptions"
private const val UNLIMITED_SLOT_SUBSCRIPTION = "plant_unlimited_slots_subsciption"

private fun subscriptionProduct(productId: String): QueryProductDetailsParams.Product {
    return QueryProductDetailsParams.Product.newBuilder().run {
        setProductId(productId)
        setProductType(BillingClient.ProductType.SUBS)
        build()
    }
}

private val subscriptionProductList = listOf(
    subscriptionProduct(SIMPLE_SLOT_SUBSCRIPTION),
    subscriptionProduct(BASE_SLOT_SUBSCRIPTION),
    subscriptionProduct(UNLIMITED_SLOT_SUBSCRIPTION),
)

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

val purchasesUpdatedListener by lazy {
    PurchasesUpdatedListener { billingResult, purchases ->
        // TODO provide propper analytics here

        logSeparator()

        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            logBilling("User has just bought following products:")

            for (purchase in purchases) {
                logBilling("Bought product = $purchase")
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            logBilling("User cancelled billing.")

            if (purchases != null) {
                for (purchase in purchases) {
                    logBilling("Cancelled product = $purchase")
                }
            }
        } else {
            logBilling("Unexpected billing response code: ${billingResult.responseCode} for products $purchases")
        }

        logSeparator()
    }
}

fun logBilling(message: String) {
    Timber.d("Billing | $message")
}

val BillingClient.isConnected: Boolean
    get() = connectionState == BillingClient.ConnectionState.CONNECTED
