package com.example.planthelper.models.ui.task

import androidx.compose.runtime.Immutable
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.task.Task

@Immutable
data class CompositeTask(
    val plant: Plant,
    val task: Task,
    val schedule: Schedule,
)