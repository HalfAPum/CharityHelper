package com.narvatov.planthelper.models.ui.purchase

import androidx.compose.ui.graphics.Color
import com.android.billingclient.api.ProductDetails

data class SubscriptionDetails(
    val name: String,
    val newSlotsText: String,
    val dailyTasksText: String,
    val noAds: String? = null,
    val newSlotsBoldText: String = "",
    val dailyTasksBoldText: String = "",
    val headerText: String? = null,
    val backgroundColor: Color = Color.White,
    var productDetails: ProductDetails? = null
)