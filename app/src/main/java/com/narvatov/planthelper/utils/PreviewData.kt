package com.narvatov.planthelper.utils

import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.schedule.ScheduleType
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.ui.task.CompositeTask
import java.util.*

val previewPlant = Plant(
    name = "Cucumber",
    originName = "Cucumber original",
    imageUrl = "https://whatflower.net/wp-content/uploads/2017/10/5-800x500.jpg",
    health = 0.78,
)
val previewTask = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = Date(),
)
val previewTaskCompleted = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = Date(),
    status = TaskStatus.Completed
)
val previewTaskFailed = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = Date(),
    status = TaskStatus.Failed
)
val previewSchedule = Schedule(
    plantName = "Cucumber",
    scheduleType = ScheduleType.Watering,
    name = "Some additional info",
    monthSchedule = mapOf()
)


val taskTestData = listOf(
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTaskCompleted,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTaskFailed,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTaskFailed,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTaskCompleted,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
)