package com.narvatov.planthelper.data.utils

import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.utils.MonthYear
import com.narvatov.planthelper.utils.asString

fun Schedule.throwIllegalMonthException(): Nothing = throw IllegalArgumentException(
    "Empty ${scheduleType.name} schedule for plant with id: $plantName"
)

context (MonthYear)
val Schedule.scheduledMonthRepetitions: Int
    get() = monthSchedule[this@MonthYear.asString] ?: 0

context (MonthYear)
val Schedule.monthRepetitionsAreAtLeastOne: Boolean
    get() = scheduledMonthRepetitions >= 1