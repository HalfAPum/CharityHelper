package com.narvatov.planthelper.data.repository.billing

import com.android.billingclient.api.BillingClient
import com.narvatov.planthelper.data.utils.isConnected
import com.narvatov.planthelper.data.utils.logBilling
import com.narvatov.planthelper.data.utils.startConnection
import com.narvatov.planthelper.models.ui.purchase.BillingState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.core.annotation.Single

@Single
class BillingConnector(
    private val billingClient: BillingClient,
    private val billingPurchaseProcessor: BillingPurchaseProcessor,
) {

    private val _billingConnectionFlow = MutableSharedFlow<BillingState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val billingConnectionFlow = _billingConnectionFlow.asSharedFlow()


    fun connectToBilling() {
        logBilling("Try to connect to billing")

        if (billingClient.isConnected) {
            logBilling("Billing is already connected. Reusing current connection...")
        }

        logBilling("Connecting...")

        _billingConnectionFlow.tryEmit(BillingState.Loading)

        billingClient.startConnection(
            onSetupFinished = { billingResult ->
                // TODO ADD ANALYTICS
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    logBilling("Connected to billing successfully")

                    billingPurchaseProcessor.processUnhandledPurchases()

                    billingPurchaseProcessor.processPurchases(_billingConnectionFlow)
                } else {
                    logBilling("Connected to billing unsuccessfully. $billingResult")

                    _billingConnectionFlow.tryEmit(BillingState.Error)
                }
            },
            onDisconnected = {
                logBilling("Billing has been disconnected")

                _billingConnectionFlow.tryEmit(BillingState.Error)
            }
        )
    }

}