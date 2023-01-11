package com.narvatov.planthelper.data.utils

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Transformations.map
import com.android.billingclient.api.*
import com.narvatov.planthelper.models.data.local.BillingSubscription
import com.narvatov.planthelper.models.ui.purchase.SubscriptionDetails
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

val productIdList = listOf(
    SIMPLE_SLOT_SUBSCRIPTION,
    BASE_SLOT_SUBSCRIPTION,
    UNLIMITED_SLOT_SUBSCRIPTION,
)

val subscriptionIdsToSlotsMap = mapOf(
    SIMPLE_SLOT_SUBSCRIPTION to 4,
    BASE_SLOT_SUBSCRIPTION to 6,
    UNLIMITED_SLOT_SUBSCRIPTION to Int.MAX_VALUE,
)

val lightSubscriptionDetails = SubscriptionDetails(
    "Light",
    "2 extra slots for plants",
    "no ads",
    "10 tasks daily"
)

val mediumSubscriptionDetails = SubscriptionDetails(
    name = "Medium",
    newSlotsText = "6 extra slots for plants",
    noAds = "no ads",
    dailyTasksText = "50 tasks daily",
    headerText = "BEST CHOICE",
    backgroundColor = Color(0xFFCEFFCA)
)

val unlimitedSubscriptionDetails = SubscriptionDetails(
    "Unlimited",
    "Unlimited slots for plants",
    "no ads",
    "Unlimited tasks daily"
)

val subscriptionIdsToSubscriptionDetails = mapOf(
    SIMPLE_SLOT_SUBSCRIPTION to lightSubscriptionDetails,
    BASE_SLOT_SUBSCRIPTION to mediumSubscriptionDetails,
    UNLIMITED_SLOT_SUBSCRIPTION to unlimitedSubscriptionDetails,
)

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