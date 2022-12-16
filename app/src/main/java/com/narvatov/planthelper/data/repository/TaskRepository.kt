package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.monthRepetitionsAreAtLeastOne
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
    private val plantRepository: PlantRepository,
) : Repository() {

    fun taskFlow(plantId: Long?) = plantId?.let {
        taskDao.flowByPlantId(plantId)
    } ?: taskDao.flowAll()

    suspend fun getTask(taskId: Long) = IOOperation {
        taskDao.get(taskId)
    }

    suspend fun updateTaskStatus(task: Task, status: TaskStatus) = IOOperation {
        val updatedTask = task.copy(status = status)

        taskDao.update(updatedTask)

        tryCreateNextTask(oldTask = updatedTask)
    }

    private suspend fun tryCreateNextTask(oldTask: Task) = IOOperation {
        val scheduledQueueSize = taskDao.getAllByPlantId(oldTask.plantId)
            .filter { it.status == TaskStatus.Scheduled }
            .filterByTaskScheduleType(oldTask)
            .count()

        if (scheduledQueueSize >= MINIMUM_AMOUNT_OF_ACTIVE_TASKS) return@IOOperation

        generateNextTask(oldTask = oldTask)
    }

    private suspend fun generateNextTask(oldTask: Task) {
        val latestTask = taskDao.getAllByPlantId(oldTask.plantId)
            .filterByTaskScheduleType(oldTask)
            .maxBy { it.scheduledDate.time }

        val taskSchedule = scheduleDao.get(oldTask.scheduleId)

        taskSchedule.generateTask(
            plantId = oldTask.plantId,
            dateStartLimit = latestTask.scheduledDate,
        )
    }

    private suspend inline fun List<Task>.filterByTaskScheduleType(oldTask: Task) = filter { newTask ->
        val newSchedule = scheduleDao.get(newTask.scheduleId)
        val oldSchedule = scheduleDao.get(oldTask.scheduleId)

        newSchedule.scheduleType == oldSchedule.scheduleType
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
    ) = schedules.map { it.generateTask(plantId) }

    private fun Schedule.generateTask(
        plantId: Long,
        dateStartLimit: Date = Date(),
    ) = Task(
        plantId = plantId,
        scheduleId = id,
        name = name ?: scheduleType.action,
        healthImpact = scheduleType.healthImpact,
        scheduledDate = generateTaskDate(dateStartLimit = dateStartLimit),
    )

    private fun Schedule.generateTaskDate(dateStartLimit: Date = Date()): Date {
        forEachMonth(startPoint = dateStartLimit) {
            val periodBetweenTasksInMonth = totalDaysInMonth /
                    scheduledMonthRepetitions.plus(1)

            if (monthRepetitionsAreAtLeastOne) {
                var taskDay = 0.0

                //TODO provide appropriate naming instead of monthDay
                while(true) {
                    taskDay += periodBetweenTasksInMonth

                    if (taskDay < dateStartLimit.monthDay) continue

                    if (taskDay > monthDay) return taskDay.toDate()
                }
            }
        }

        throwIllegalMonthException()
    }

    suspend fun syncTasksStatus() = IOOperation {
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

    companion object {
        private const val MINIMUM_AMOUNT_OF_ACTIVE_TASKS = 2
    }

}