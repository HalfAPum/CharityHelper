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

suspend inline fun List<Task>.filterByTaskScheduleType(
    oldTaskScheduleId: Long,
    scheduleDao: ScheduleDao,
) = filter { newTask ->
    val newSchedule = scheduleDao.get(newTask.scheduleId)
    val oldSchedule = scheduleDao.get(oldTaskScheduleId)

    newSchedule.scheduleType == oldSchedule.scheduleType
}