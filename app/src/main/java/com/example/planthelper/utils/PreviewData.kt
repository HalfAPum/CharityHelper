package com.example.planthelper.utils

import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.schedule.ScheduleType
import com.example.planthelper.models.data.local.task.Task

val previewPlant = Plant(
    name = "Cucumber",
    originName = "Cucumber original",
    imageUrl = "https://whatflower.net/wp-content/uploads/2017/10/5-800x500.jpg",
    age = 12,
    health = 0.78,
)
val previewTask = Task(
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = "Tomorrow",
)
val previewSchedule = Schedule(
    plantId = -1,
    scheduleType = ScheduleType.Watering,
    name = "Some additional info",
    monthSchedule = mapOf()
)