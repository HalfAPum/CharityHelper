package com.narvatov.planthelper.models.ui.purchase

import com.android.billingclient.api.ProductDetails

interface BillingState {

    object Loading : BillingState

    class Success(val productDetailsList: List<ProductDetails>) : BillingState

    class Error(val errorMessage: String) : BillingState

}