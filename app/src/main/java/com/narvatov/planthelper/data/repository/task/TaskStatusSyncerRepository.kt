package com.narvatov.planthelper.data.repository.task

import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.data.local.task.isAtLeast
import org.koin.core.annotation.Factory
import java.util.*

@Factory
class TaskStatusSyncerRepository(
    private val taskRepository: TaskRepository,
) : Repository() {

    suspend fun syncFailedTasks() = IOOperation {
        val failedTasks = taskRepository.getAllTasks()
            .filter { task -> task.status.isAtLeast(TaskStatus.Active) }
            .filter { task ->
                val currentDate = Date()
                task.scheduledDate.before(currentDate)
            }
            .filter { task ->
                val taskCalendar = Calendar.Builder().setInstant(task.scheduledDate).build()
                val currentCalendar = Calendar.getInstance()

                taskCalendar[Calendar.DAY_OF_YEAR] != currentCalendar[Calendar.DAY_OF_YEAR]
            }

        taskRepository.updateTasksStatuses(failedTasks, TaskStatus.Failed)
    }

}