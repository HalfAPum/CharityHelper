package com.narvatov.planthelper.data.repository.task

import android.content.Context
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.data.local.task.isAtMost
import com.narvatov.planthelper.utils.cancelScheduledNotifications
import org.koin.core.annotation.Factory

@Factory
class TaskRepository(
    private val context: Context,
    private val taskDao: TaskDao,
    private val taskGeneratorRepository: TaskGeneratorRepository,
) : Repository() {

    suspend fun getAllTasks() = IOOperation { taskDao.getAll() }

    fun taskFlow(plantId: Long?) = plantId?.let {
        taskDao.flowByPlantId(plantId)
    } ?: taskDao.flowAll()


    suspend fun getTask(taskId: Long) = IOOperation {
        taskDao.get(taskId)
    }


    suspend fun updateTaskStatus(task: Task, status: TaskStatus) = IOOperation {
        val updatedTask = task.copy(status = status)
        taskDao.update(updatedTask)

        if (status.isAtMost(TaskStatus.Failed)) {
            task.cancelScheduledNotifications(context)
        }

        taskGeneratorRepository.tryGenerateNextTask(oldTask = updatedTask)
    }

    suspend fun updateTasksStatuses(tasks: List<Task>, status: TaskStatus) {
        tasks.forEach { task -> updateTaskStatus(task, status) }
    }

    suspend fun completeAllFailedTasks() = IOOperation {
        completeFailedTasks(tasks = getAllTasks())
    }

    suspend fun completePlantFailedTasks(plantId: Long) = IOOperation {
        completeFailedTasks(tasks = taskDao.getAllByPlantId(plantId))
    }

    private suspend fun completeFailedTasks(tasks: List<Task>) = IOOperation {
        val failedTasks = tasks.filter { it.status == TaskStatus.Failed }

        updateTasksStatuses(failedTasks, TaskStatus.Completed)
    }

    suspend fun deleteTasks(plantId: Long) = IOOperation {
        val plantTasks = taskDao.getAllByPlantId(plantId)

        plantTasks.cancelScheduledNotifications(context)

        taskDao.delete(plantId)
    }

}