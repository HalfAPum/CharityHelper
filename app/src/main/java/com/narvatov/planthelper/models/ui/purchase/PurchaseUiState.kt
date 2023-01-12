package com.narvatov.planthelper.models.ui.purchase

import androidx.compose.runtime.Immutable
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.narvatov.planthelper.data.utils.toSubscriptionDetailsList
import com.narvatov.planthelper.models.data.local.BillingSubscription

@Immutable
data class PurchaseUiState(
    val loading: Boolean,
    val subscriptionDetailsList: List<SubscriptionDetails>,
    val error: Boolean,
    val purchasedList: List<BillingSubscription> = emptyList(),
)

fun EmptyPurchaseUiState() = PurchaseUiState(false, emptyList(), false)

fun LoadingPurchaseUiState() = PurchaseUiState(true, emptyList(), false)

fun SuccessfulPurchaseUiState(
    billingState: BillingState.Success,
) = PurchaseUiState(
    false,
    billingState.productDetailsList.toSubscriptionDetailsList(),
    false,
)

fun ErrorPurchaseUiState() = PurchaseUiState(false, emptyList(), true)