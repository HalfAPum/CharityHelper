package com.narvatov.planthelper.domain.task

import com.narvatov.planthelper.models.ui.task.TaskUIElement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import java.util.*

@Factory
class HeaderActiveCompositeTaskFlowUseCase(
    private val activeCompositeTaskFlowUseCase: ActiveCompositeTaskFlowUseCase,
) {

    operator fun invoke(plantId: Long?): Flow<List<TaskUIElement>> {
        return activeCompositeTaskFlowUseCase.invoke(plantId).map { compositeTasks ->
            val resultActiveTaskUIElements = LinkedList<TaskUIElement>()

            compositeTasks.forEach { compositeTask ->
                addTodayActivityHeader(compositeTask, resultActiveTaskUIElements)

                addFutureActivityHeader(compositeTask, resultActiveTaskUIElements)


                resultActiveTaskUIElements.add(compositeTask)
            }

            resultActiveTaskUIElements
        }
    }

    private fun addTodayActivityHeader(
        compositeTask: TaskUIElement.CompositeTask,
        resultActiveTaskUIElements: MutableCollection<TaskUIElement>,
    ) {
        val taskCalendar = Calendar.Builder().setInstant(compositeTask.task.scheduledDate).build()
        val currentCalendar = Calendar.getInstance()

        val isHeaderNotAdded = resultActiveTaskUIElements.contains(TaskUIElement.Header.TodayActivity).not()

        val isScheduledOnToday = taskCalendar[Calendar.DAY_OF_YEAR] == currentCalendar[Calendar.DAY_OF_YEAR]
        if (isHeaderNotAdded && isScheduledOnToday) {
            resultActiveTaskUIElements.add(TaskUIElement.Header.TodayActivity)
        }
    }

    private fun addFutureActivityHeader(
        compositeTask: TaskUIElement.CompositeTask,
        resultActiveTaskUIElements: MutableCollection<TaskUIElement>,
    ) {
        val taskCalendar = Calendar.Builder().setInstant(compositeTask.task.scheduledDate).build()
        val currentCalendar = Calendar.getInstance()

        val isHeaderNotAdded = resultActiveTaskUIElements.contains(TaskUIElement.Header.FutureActivity).not()

        val isScheduledOnFuture = taskCalendar[Calendar.DAY_OF_YEAR] > currentCalendar[Calendar.DAY_OF_YEAR]
        if (isHeaderNotAdded && isScheduledOnFuture) {
            resultActiveTaskUIElements.add(TaskUIElement.Header.FutureActivity)
        }
    }

}