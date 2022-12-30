package com.narvatov.planthelper.data.repository.task

import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.TaskCreator.generateFirstPlantTasks
import com.narvatov.planthelper.data.utils.TaskCreator.createTask
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import org.koin.core.annotation.Factory

@Factory
class TaskRepository(
    private val scheduleDao: ScheduleDao,
    private val taskDao: TaskDao,
) : Repository() {

    fun taskFlow(plantId: Long?) = plantId?.let {
        taskDao.flowByPlantId(plantId)
    } ?: taskDao.flowAll()


    suspend fun getTask(taskId: Long) = IOOperation {
        taskDao.get(taskId)
    }

    suspend fun getNextNotificationTask(plantId: Long, scheduleId: Long) = IOOperation {
        taskDao.getAllByPlantId(plantId)
            .filter { it.status == TaskStatus.Scheduled }
            .filterByTaskScheduleType(scheduleId)
            .minBy { it.scheduledDate.time }
    }


    suspend fun updateTaskStatus(task: Task, status: TaskStatus) = IOOperation {
        val updatedTask = task.copy(status = status)

        taskDao.update(updatedTask)

        tryGenerateNextTask(oldTask = updatedTask)
    }

    private suspend fun tryGenerateNextTask(oldTask: Task) = IOOperation {
        val scheduledQueueSize = taskDao.getAllByPlantId(oldTask.plantId)
            .filter { it.status == TaskStatus.Scheduled }
            .filterByTaskScheduleType(oldTask.scheduleId)
            .count()

        if (scheduledQueueSize >= MINIMUM_ACTIVE_TASKS) return@IOOperation

        generateNextTask(oldTask = oldTask)
    }

    suspend fun generateNextTask(oldTask: Task) {
        val latestTask = taskDao.getAllByPlantId(oldTask.plantId)
            .filterByTaskScheduleType(oldTask.scheduleId)
            .maxBy { it.scheduledDate.time }

        val taskSchedule = scheduleDao.get(oldTask.scheduleId)


        val generatedTask = taskSchedule.createTask(
            plantId = oldTask.plantId,
            dateStartLimit = latestTask.scheduledDate,
        )

        taskDao.insert(generatedTask)
    }

    private suspend inline fun List<Task>.filterByTaskScheduleType(
        oldTaskScheduleId: Long,
    ) = filter { newTask ->
        val newSchedule = scheduleDao.get(newTask.scheduleId)
        val oldSchedule = scheduleDao.get(oldTaskScheduleId)

        newSchedule.scheduleType == oldSchedule.scheduleType
    }


    suspend fun generateFirstTasksForPlant(plant: Plant) = IOOperation {
        val plantSchedules = scheduleDao.getSchedulesByPlantOriginName(plant.originName)

        val tasks = generateFirstPlantTasks(schedules = plantSchedules, plantId = plant.id)

        taskDao.insert(tasks)
    }


    companion object {
        private const val MINIMUM_ACTIVE_TASKS = 2
    }

}