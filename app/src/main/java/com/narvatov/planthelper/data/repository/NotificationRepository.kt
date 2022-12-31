package com.narvatov.planthelper.data.repository

import android.content.Context
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.utils.scheduleNotification
import org.koin.core.annotation.Factory

@Factory
class NotificationRepository(
    private val taskDao: TaskDao,
    private val context: Context
) : Repository() {

    suspend fun scheduleNotification(task: Task) = IOOperation {
        val scheduledTask = task.scheduleNotification(context)

        taskDao.update(scheduledTask)
    }

    suspend fun scheduleNotification(taskId: Long) {
        val task = taskDao.get(taskId) ?: return

        scheduleNotification(task)
    }

    suspend fun scheduleNotifications(tasksIds: List<Long>) {
        tasksIds.forEach { taskId -> scheduleNotification(taskId) }
    }

}