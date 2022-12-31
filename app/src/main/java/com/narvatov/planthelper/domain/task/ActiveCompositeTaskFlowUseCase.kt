package com.narvatov.planthelper.domain.task

import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.data.local.task.isAtLeast
import com.narvatov.planthelper.models.ui.task.CompositeTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class ActiveCompositeTaskFlowUseCase(
    private val compositeTaskFlowUseCase: CompositeTaskFlowUseCase,
) {

    operator fun invoke(plantId: Long?): Flow<List<CompositeTask>> {
        return compositeTaskFlowUseCase.invoke(plantId).map { compositeTasks ->
            compositeTasks.filter {
                it.task.status.isAtLeast(TaskStatus.Active)
            }.sortedBy { it.task.scheduledDate }
        }
    }

}