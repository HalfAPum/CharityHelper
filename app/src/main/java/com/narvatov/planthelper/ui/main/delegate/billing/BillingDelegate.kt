package com.narvatov.planthelper.ui.main.delegate.billing

import com.narvatov.planthelper.data.repository.BillingRepository
import com.narvatov.planthelper.utils.inject

object BillingDelegate : IBillingDelegate {

    private val billingRepository: BillingRepository by inject()

    override fun connectToBilling() {
        billingRepository.connectToBilling()
    }

}