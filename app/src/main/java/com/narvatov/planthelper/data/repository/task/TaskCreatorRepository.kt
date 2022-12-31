package com.narvatov.planthelper.data.repository.task

import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.*
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task
import org.koin.core.annotation.Single
import java.util.*

object TaskCreatorRepository : Repository() {

    fun generateFirstPlantTasks(
        schedules: List<Schedule>,
        plantId: Long,
    ) = schedules.map { it.createTask(plantId) }

    fun Schedule.createTask(
        plantId: Long,
        dateStartLimit: Date = Date(),
    ) = Task(
        plantId = plantId,
        scheduleId = id,
        name = name ?: scheduleType.action,
        healthImpact = scheduleType.healthImpact,
        scheduledDate = createTaskDate(dateStartLimit = dateStartLimit),
    )

    private fun Schedule.createTaskDate(dateStartLimit: Date = Date()): Date {
        forEachMonth(startPoint = dateStartLimit) {
            val periodBetweenTasksInMonth = totalDaysInMonth / scheduledMonthRepetitions

            if (monthRepetitionsAreAtLeastOne) {
                var taskDay = periodBetweenTasksInMonth / 2

                taskDay -= periodBetweenTasksInMonth

                while(true) {
                    taskDay += periodBetweenTasksInMonth

                    when {
                        taskDay <= dateStartLimit.monthDay -> continue
                        taskDay > totalDaysInMonth -> break
                        taskDay > monthDay -> return taskDay.toDate()
                    }
                }
            }
        }

        throwIllegalMonthException()
    }

}