package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.datasource.local.dao.TaskDao
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.data.utils.monthRepetitionsAreAtLeastNotZero
import com.example.planthelper.data.utils.scheduledMonthRepetitions
import com.example.planthelper.data.utils.throwIllegalMonthException
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.task.Task
import com.example.planthelper.models.data.local.task.TaskStatus
import com.example.planthelper.utils.forEachMonth
import com.example.planthelper.utils.monthDay
import com.example.planthelper.utils.toDate
import com.example.planthelper.utils.totalDaysInMonth
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class TaskRepository(
    private val scheduleDao: ScheduleDao,
    private val taskDao: TaskDao,
) : Repository() {

    fun taskFlow(plantId: Long?) = plantId?.let {
        taskDao.flowByPlantId(plantId).map {
            println("PRINT SHITTY TASK $it")
            it
        }
    } ?: taskDao.flowAll().map {
        println("PRINT SHITTY TASK $it")
        it
    }

    suspend fun completeTask(task: Task) {
        val completedTask = task.copy(status = TaskStatus.Completed)

        taskDao.update(completedTask)
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

    private fun Schedule.generateFirstTaskDate(): String {
        var resultTaskDate: String? = null
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