package com.example.planthelper.models.data.local.schedule

import androidx.annotation.DrawableRes
import com.example.planthelper.R

enum class ScheduleType(
    val action: String,
    val healthImpact: Double,
    @DrawableRes
    val icon: Int
) {
    Watering("Watering", 0.02, R.drawable.ic_watering),
    Fertilizer("Fertilize", 0.05, R.drawable.ic_fertilize),
    Pruning("Cut off", 0.1, R.drawable.ic_complete),
}

val ScheduleType.healthPlusPercentage: String
    get() = "+${healthImpact * 100}%"