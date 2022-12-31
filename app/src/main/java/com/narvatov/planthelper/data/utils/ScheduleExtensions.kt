package com.narvatov.planthelper.data.utils

import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task

fun Schedule.throwIllegalMonthException(): Nothing = throw IllegalArgumentException(
    "Empty ${scheduleType.name} schedule for plant with id: $plantName"
)

context (MonthYear)
val Schedule.scheduledMonthRepetitions: Int
    get() = monthSchedule[this@MonthYear.asString] ?: 0

context (MonthYear)
val Schedule.monthRepetitionsAreAtLeastOne: Boolean
    get() = scheduledMonthRepetitions >= 1

fun List<Task>.filterByTaskScheduleType(
    oldTaskScheduleId: Long,
) = filter { newTask ->
    newTask.scheduleId == oldTaskScheduleId
}

fun List<Task>.filterByTaskScheduleType(
    oldTask: Task,
) = filter { newTask ->
    newTask.scheduleId == oldTask.scheduleId
}