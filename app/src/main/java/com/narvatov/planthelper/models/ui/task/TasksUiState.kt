package com.narvatov.planthelper.models.ui.task

import androidx.compose.runtime.Immutable

@Immutable
data class TasksUiState(
    val activeTasks: List<TaskUIElement>,
    val historyTasks: List<TaskUIElement.CompositeTask>,
)

fun EmptyTasksUiState() = TasksUiState(emptyList(), emptyList())
