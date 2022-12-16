package com.narvatov.planthelper.data.repository.task

import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import org.koin.core.annotation.Factory
import java.util.*

@Factory
class TaskStatusSyncerRepository(
    private val taskDao: TaskDao,
) : Repository() {

    suspend fun sync() = IOOperation {
        val failedTasks = taskDao.getAll()
            .filter { task ->
                task.status == TaskStatus.Scheduled
                        || task.status == TaskStatus.Active
            }
            .filter { task ->
                val currentDate = Date()
                task.scheduledDate.after(currentDate)
            }
            .filter { task ->
                val taskCalendar = Calendar.Builder().setInstant(task.scheduledDate).build()
                val currentCalendar = Calendar.getInstance()

                taskCalendar[Calendar.DAY_OF_YEAR] != currentCalendar[Calendar.DAY_OF_YEAR]
            }
            .map { it.copy(status = TaskStatus.Failed) }

        taskDao.update(failedTasks)
    }

}