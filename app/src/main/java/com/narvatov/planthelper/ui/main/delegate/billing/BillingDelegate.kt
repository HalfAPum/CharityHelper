package com.narvatov.planthelper.ui.main.delegate.billing

import androidx.activity.ComponentActivity
import com.narvatov.planthelper.data.repository.BillingRepository
import com.narvatov.planthelper.utils.inject

class BillingDelegate : IBillingDelegate {

    private val billingRepository: BillingRepository by inject()

    override fun ComponentActivity.connectToBilling() {
        billingRepository.connectToBilling()
    }

}