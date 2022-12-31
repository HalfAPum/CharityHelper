package com.narvatov.planthelper.data.repository.task

import android.content.Context
import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.NotificationRepository
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.filterByTaskScheduleType
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.data.local.task.isAtMost
import com.narvatov.planthelper.utils.cancelScheduledNotification
import org.koin.core.annotation.Factory

@Factory
class TaskRepository(
    private val context: Context,
    private val taskDao: TaskDao,
    private val scheduleDao: ScheduleDao,
    private val taskGeneratorRepository: TaskGeneratorRepository,
) : Repository() {

    suspend fun getAllTasks() = IOOperation { taskDao.getAll() }

    fun taskFlow(plantId: Long?) = plantId?.let {
        taskDao.flowByPlantId(plantId)
    } ?: taskDao.flowAll()


    suspend fun getTask(taskId: Long) = IOOperation {
        taskDao.get(taskId)
    }

    suspend fun getNextNotificationTask(plantId: Long, scheduleId: Long) = IOOperation {
        taskDao.getAllByPlantId(plantId)
            .filter { it.status == TaskStatus.Scheduled }
            .filterByTaskScheduleType(scheduleId, scheduleDao)
            .minBy { it.scheduledDate.time }
    }


    suspend fun updateTaskStatus(task: Task, status: TaskStatus) = IOOperation {
        val updatedTask = task.copy(status = status)
        taskDao.update(updatedTask)

        if (status.isAtMost(TaskStatus.Failed)) {
            task.cancelScheduledNotification(context)
        }

        taskGeneratorRepository.tryGenerateNextTask(oldTask = updatedTask)
    }

    suspend fun updateTasksStatuses(tasks: List<Task>, status: TaskStatus) {
        tasks.forEach { task -> updateTaskStatus(task, status) }
    }

}