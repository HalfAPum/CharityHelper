package com.narvatov.planthelper.data.utils

import androidx.compose.ui.graphics.Color
import com.narvatov.planthelper.models.ui.purchase.SubscriptionDetails

// Available subscription
private const val SIMPLE_SLOT_SUBSCRIPTION = "plant_slots_subscriptions"
private const val BASE_SLOT_SUBSCRIPTION = "plant_4_slots_subscriptions"
private const val UNLIMITED_SLOT_SUBSCRIPTION = "plant_unlimited_slots_subsciption"

val productIdList = listOf(
    SIMPLE_SLOT_SUBSCRIPTION,
    BASE_SLOT_SUBSCRIPTION,
    UNLIMITED_SLOT_SUBSCRIPTION,
)

val subscriptionIdsToSlotsMap = mapOf(
    SIMPLE_SLOT_SUBSCRIPTION to 4,
    BASE_SLOT_SUBSCRIPTION to 6,
    UNLIMITED_SLOT_SUBSCRIPTION to Int.MAX_VALUE,
)

val lightSubscriptionDetails = SubscriptionDetails(
    name = "Light",
    newSlotsBoldText = "2 extra",
    newSlotsText = " slots for plants",
    noAds = "no ads",
    dailyTasksBoldText = "10",
    dailyTasksText = " tasks daily",
)

val mediumSubscriptionDetails = SubscriptionDetails(
    name = "Medium",
    newSlotsBoldText = "6 extra",
    newSlotsText = " slots for plants",
    noAds = "no ads",
    dailyTasksBoldText = "50",
    dailyTasksText = " tasks daily",
    headerText = "BEST CHOICE",
    backgroundColor = Color(0xFFCEFFCA)
)

val unlimitedSubscriptionDetails = SubscriptionDetails(
    name = "Unlimited",
    newSlotsBoldText = "Unlimited",
    newSlotsText = " slots for plants",
    noAds = "no ads",
    dailyTasksBoldText = "Unlimited",
    dailyTasksText = " tasks daily"
)

val subscriptionIdsToSubscriptionDetails = mapOf(
    SIMPLE_SLOT_SUBSCRIPTION to lightSubscriptionDetails,
    BASE_SLOT_SUBSCRIPTION to mediumSubscriptionDetails,
    UNLIMITED_SLOT_SUBSCRIPTION to unlimitedSubscriptionDetails,
)