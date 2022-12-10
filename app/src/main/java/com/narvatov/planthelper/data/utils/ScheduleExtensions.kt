package com.narvatov.planthelper.data.utils

import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.utils.Month
import com.narvatov.planthelper.utils.asString

fun Schedule.throwIllegalMonthException(): Nothing = throw IllegalArgumentException(
    "Empty ${scheduleType.name} schedule for plant with id: $plantName"
)

context (Month)
val Schedule.scheduledMonthRepetitions: Int
    get() = monthSchedule[this@Month.asString] ?: 0

context (Month)
val Schedule.monthRepetitionsAreAtLeastNotZero: Boolean
    get() = scheduledMonthRepetitions > 0