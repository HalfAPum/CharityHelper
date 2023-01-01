package com.narvatov.planthelper.models.ui.task

import androidx.compose.runtime.Immutable
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task

sealed interface TaskUIElement {

    @Immutable
    data class CompositeTask(
        val plant: Plant,
        val task: Task,
        val schedule: Schedule,
    ): TaskUIElement

    @Immutable
    enum class Header(val text: String) : TaskUIElement {
        TodayActivity("Today activity"),
        FutureActivity("Future activity"),
    }

}