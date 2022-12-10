package com.narvatov.planthelper.models.ui.task

import androidx.compose.runtime.Immutable

@Immutable
data class TasksUiState(
    val activeTasks: List<CompositeTask>,
    val historyTasks: List<CompositeTask>,
)

fun EmptyTasksUiState() = TasksUiState(emptyList(), emptyList())
