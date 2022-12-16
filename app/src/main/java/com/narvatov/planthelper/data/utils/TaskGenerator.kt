package com.narvatov.planthelper.data.utils

import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task
import java.util.*

object TaskGenerator {

    fun generateFirstPlantTasks(
        schedules: List<Schedule>,
        plantId: Long,
    ) = schedules.map { it.generateTask(plantId) }

    fun Schedule.generateTask(
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

}