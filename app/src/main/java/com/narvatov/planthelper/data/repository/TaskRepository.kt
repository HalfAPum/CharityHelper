package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.monthRepetitionsAreAtLeastNotZero
import com.narvatov.planthelper.data.utils.scheduledMonthRepetitions
import com.narvatov.planthelper.data.utils.throwIllegalMonthException
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.utils.forEachMonth
import com.narvatov.planthelper.utils.monthDay
import com.narvatov.planthelper.utils.toDate
import com.narvatov.planthelper.utils.totalDaysInMonth
import org.koin.core.annotation.Factory
import java.util.*

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

    suspend fun completeTask(task: Task) {
        val completedTask = task.copy(status = TaskStatus.Completed)

        taskDao.update(completedTask)
    }

    suspend fun markTaskNotificationShown(taskId: Long) = IOOperation {
        taskDao.markTaskNotificationShown(taskId)
    }

    suspend fun getNextNotificationTask(plantId: Long, scheduleId: Long) = IOOperation {
        taskDao.getNextNotificationTasks(plantId, scheduleId)
            .first { it.status == TaskStatus.Scheduled }
    }

    suspend fun generateFirstTasksForPlant(plant: Plant) = IOOperation {
        val plantSchedules = scheduleDao.getSchedulesByPlantOriginName(plant.originName)

        val tasks = generateFirstPlantTasks(schedules = plantSchedules, plantId = plant.id)

        taskDao.insert(tasks)
    }

    private fun generateFirstPlantTasks(
        schedules: List<Schedule>,
        plantId: Long,
    ) = schedules.map { it.generateFirstTask(plantId) }

    private fun Schedule.generateFirstTask(plantId: Long) = Task(
        plantId = plantId,
        scheduleId = id,
        name = name ?: scheduleType.action,
        healthImpact = scheduleType.healthImpact,
        scheduledDate = generateFirstTaskDate(),
    )

    private fun Schedule.generateFirstTaskDate(): Date {
        var resultTaskDate: Date? = null
        forEachMonth {
            val periodBetweenTasksInMonth = totalDaysInMonth /
                    scheduledMonthRepetitions.plus(1)

            if (monthRepetitionsAreAtLeastNotZero) {
                var taskDay = periodBetweenTasksInMonth

                while(true) {
                    if (taskDay > monthDay) {
                        resultTaskDate = taskDay.toDate()
                        break
                    }
                    taskDay += periodBetweenTasksInMonth
                }
            }
        }

        return resultTaskDate ?: throwIllegalMonthException()
    }

}