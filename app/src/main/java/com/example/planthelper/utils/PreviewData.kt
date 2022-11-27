package com.example.planthelper.utils

import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.schedule.ScheduleType
import com.example.planthelper.models.data.local.task.Task
import com.example.planthelper.models.data.local.task.TaskStatus
import com.example.planthelper.models.ui.task.CompositeTask

val previewPlant = Plant(
    name = "Cucumber",
    originName = "Cucumber original",
    imageUrl = "https://whatflower.net/wp-content/uploads/2017/10/5-800x500.jpg",
    age = 12,
    health = 0.78,
)
val previewTask = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = "Tomorrow",
)
val previewTaskCompleted = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = "Tomorrow",
    status = TaskStatus.Completed
)
val previewTaskFailed = Task(
    plantId = 1,
    scheduleId = 1,
    name = "Watering",
    healthImpact = 3.0,
    scheduledDate = "Tomorrow",
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