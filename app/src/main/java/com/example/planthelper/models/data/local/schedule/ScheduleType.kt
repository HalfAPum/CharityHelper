package com.example.planthelper.models.data.local.schedule

enum class ScheduleType(
    val action: String,
    val healthImpact: Double
) {
    //TODO here is example health impact fill it with real values later
    Watering("Water plant", 0.02),
    Fertilizer("Fertilize", 0.05),
    Pruning("Cut off", 0.1),
}