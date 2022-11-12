package com.example.planthelper.models.ui.purchase

data class PurchaseUiState(
    val stub: Boolean
)

fun EmptyPurchaseUiState() = PurchaseUiState(false)