package com.narvatov.planthelper.data.repository.billing

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
import kotlinx.coroutines.flow.*
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single


@Factory
class BillingRepository(
    private val billingDao: BillingDao,
    private val billingClient: BillingClient
): Repository() {

    fun launchBillingFlow(activity: Activity, billingFlowParams: BillingFlowParams) {
        logBilling("Launch purchase flow")

        billingClient.launchBillingFlow(activity, billingFlowParams)
    }

    fun flowPurchasedProducts() = billingDao.flowAll()

}