package com.narvatov.planthelper.ui.main.delegate.billing

import com.narvatov.planthelper.data.repository.billing.BillingConnector
import com.narvatov.planthelper.data.repository.billing.BillingRepository
import com.narvatov.planthelper.utils.inject

object BillingDelegate : IBillingDelegate {

    private val billingConnector: BillingConnector by inject()

    override fun connectToBilling() {
        billingConnector.connectToBilling()
    }

}