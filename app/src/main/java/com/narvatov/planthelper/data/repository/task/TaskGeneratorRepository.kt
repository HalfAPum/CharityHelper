package com.narvatov.planthelper.data.repository.task

import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.repository.task.TaskCreatorRepository.createTask
import com.narvatov.planthelper.data.utils.filterByTaskScheduleType
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import org.koin.core.annotation.Factory

@Factory
class TaskGeneratorRepository(
    private val taskDao: TaskDao,
    private val scheduleDao: ScheduleDao,
) : Repository() {

    suspend fun tryGenerateNextTask(oldTask: Task) = IOOperation {
        val scheduledQueueSize = taskDao.getAllByPlantId(oldTask.plantId)
            .filter { it.status == TaskStatus.Scheduled }
            .filterByTaskScheduleType(oldTask.scheduleId, scheduleDao)
            .count()

        if (scheduledQueueSize >= MINIMUM_ACTIVE_TASKS) return@IOOperation

        generateNextTask(oldTask = oldTask)
    }

    private suspend fun generateNextTask(oldTask: Task) {
        val latestTask = taskDao.getAllByPlantId(oldTask.plantId)
            .filterByTaskScheduleType(oldTask.scheduleId, scheduleDao)
            .maxBy { it.scheduledDate.time }

        val taskSchedule = scheduleDao.get(oldTask.scheduleId)


        val generatedTask = taskSchedule.createTask(
            plantId = oldTask.plantId,
            dateStartLimit = latestTask.scheduledDate,
        )

        taskDao.insert(generatedTask)
    }


    suspend fun generateFirstTasksForPlant(plant: Plant) = IOOperation {
        val plantSchedules = scheduleDao.getSchedulesByPlantOriginName(plant.originName)

        val tasks = TaskCreatorRepository.generateFirstPlantTasks(
            schedules = plantSchedules,
            plantId = plant.id
        )

        taskDao.insert(tasks)

        // Since MINIMUM_ACTIVE_TASKS is equal to 1 do it only one time
        generateNextTasks(tasks)
    }

    suspend fun generateNextTasks(oldTasks: List<Task>) {
        oldTasks.forEach { oldTask ->
            generateNextTask(oldTask)
        }
    }


    //TODO MOVE TO SETTING REPOSITORY
    companion object {
        private const val MINIMUM_ACTIVE_TASKS = 2
    }

}