package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.domain.CompositeTaskFlowUseCase
import com.example.planthelper.models.data.local.task.Task
import com.example.planthelper.models.ui.task.EmptyTasksUiState
import com.halfapum.general.coroutines.launchCatching
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TaskViewModel(
    private val compositeTaskFlowUseCase: CompositeTaskFlowUseCase,
    private val taskRepository: TaskRepository,
) : ViewModel() {

    var tasksUiState by mutableStateOf(EmptyTasksUiState())
        private set

    init { collectLoadPlantsFlow() }

    private fun collectLoadPlantsFlow() {
        compositeTaskFlowUseCase()
            .onEach { tasksUiState = tasksUiState.copy(activeTasks = it) }
            .launchIn(viewModelScope)
    }

    fun completeTask(task: Task) {
        launchCatching {
            taskRepository.completeTask(task)
        }
    }

}