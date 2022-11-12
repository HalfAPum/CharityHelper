package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.domain.task.ActiveCompositeTaskFlowUseCase
import com.example.planthelper.domain.task.HistoryCompositeTaskFlowUseCase
import com.example.planthelper.models.data.local.task.Task
import com.example.planthelper.models.ui.task.EmptyTasksUiState
import com.halfapum.general.coroutines.launchCatching
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TaskViewModel(
    private val plantId: Long?,
    private val activeCompositeTaskFlowUseCase: ActiveCompositeTaskFlowUseCase,
    private val historyCompositeTaskFlowUseCase: HistoryCompositeTaskFlowUseCase,
    private val taskRepository: TaskRepository,
) : ViewModel() {

    var tasksUiState by mutableStateOf(EmptyTasksUiState())
        private set

    init {
        collectActiveTasksFlow()
        collectHistoryTasksFlow()
    }

    private fun collectActiveTasksFlow() {
        activeCompositeTaskFlowUseCase(plantId)
            .onEach { tasksUiState = tasksUiState.copy(activeTasks = it) }
            .launchIn(viewModelScope)
    }

    private fun collectHistoryTasksFlow() {
        historyCompositeTaskFlowUseCase(plantId)
            .onEach { tasksUiState = tasksUiState.copy(historyTasks = it) }
            .launchIn(viewModelScope)
    }

    fun completeTask(task: Task) {
        launchCatching { taskRepository.completeTask(task) }
    }

}