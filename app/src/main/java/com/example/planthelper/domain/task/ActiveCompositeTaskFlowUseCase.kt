package com.example.planthelper.domain.task

import com.example.planthelper.models.data.local.task.TaskStatus
import com.example.planthelper.models.ui.task.CompositeTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class ActiveCompositeTaskFlowUseCase(
    private val compositeTaskFlowUseCase: CompositeTaskFlowUseCase,
) {

    operator fun invoke(): Flow<List<CompositeTask>> {
        return compositeTaskFlowUseCase.invoke().map { compositeTasks ->
            compositeTasks.filter { it.task.status == TaskStatus.Scheduled }
        }
    }

}