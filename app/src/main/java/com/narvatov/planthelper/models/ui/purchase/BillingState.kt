package com.narvatov.planthelper.models.ui.purchase

interface BillingState {

    object Loading : BillingState

    class Success(val subscriptionDetailsList: List<SubscriptionDetails>) : BillingState

    object Error : BillingState

}