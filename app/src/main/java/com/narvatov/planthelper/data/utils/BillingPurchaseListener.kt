package com.narvatov.planthelper.data.utils

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.narvatov.planthelper.utils.logSeparator

class BillingPurchaseListener(
    private val purchaseSuccessfulAction: (List<Purchase>) -> Unit = {}
) : PurchasesUpdatedListener {

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        // TODO provide propper analytics here

        logSeparator()

        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            logBilling("User has just bought following products:")

            for (purchase in purchases) {
                logBilling("Purchased product = $purchase")
            }

            purchaseSuccessfulAction(purchases)
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