package com.narvatov.planthelper.models.ui.purchase

import androidx.compose.runtime.Immutable
import com.android.billingclient.api.ProductDetails

@Immutable
data class PurchaseUiState(
    val loading: Boolean,
    val productDetailsList: List<ProductDetails>,
    val error: Boolean,
)

fun EmptyPurchaseUiState() = PurchaseUiState(false, emptyList(), false)
fun LoadingPurchaseUiState() = PurchaseUiState(true, emptyList(), false)
fun SuccessfulPurchaseUiState(
    productDetailsList: List<ProductDetails>
) = PurchaseUiState(false, productDetailsList, false)
fun ErrorPurchaseUiState() = PurchaseUiState(true, emptyList(), true)