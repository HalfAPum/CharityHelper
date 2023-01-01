package com.narvatov.planthelper.models.data.local.schedule

import androidx.annotation.DrawableRes
import com.narvatov.planthelper.R

enum class ScheduleType(
    val action: String,
    val healthImpact: Double,
    @DrawableRes
    val icon: Int,
    val notificationChannel: String
) {
    Watering(
        action = "Watering",
        healthImpact = 0.02,
        icon = R.drawable.ic_watering,
        notificationChannel = WATERING_CHANNEL_ID,
    ),
    Fertilizer(
        action = "Fertilize",
        healthImpact = 0.05,
        icon = R.drawable.ic_fertilize,
        notificationChannel = FERTILIZER_CHANNEL_ID,
    ),
    Pruning(
        action = "Cut off",
        healthImpact = 0.1,
        icon = R.drawable.ic_complete,
        notificationChannel = PRUNING_CHANNEL_ID,
    ),
}

private const val WATERING_CHANNEL_ID = "WATERING_CHANNEL_ID"
private const val FERTILIZER_CHANNEL_ID = "FERTILIZER_CHANNEL_ID"
private const val PRUNING_CHANNEL_ID = "PRUNING_CHANNEL_ID"

val ScheduleType.healthPlusPercentage: String
    get() = "+${(healthImpact * 100).toInt()} %"