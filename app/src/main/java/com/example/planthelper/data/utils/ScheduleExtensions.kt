package com.example.planthelper.data.utils

import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.utils.Month
import com.example.planthelper.utils.asString

fun Schedule.throwIllegalMonthException(): Nothing = throw IllegalArgumentException(
    "Empty ${scheduleType.name} schedule for plant with id: $plantId"
)

context (Month)
val Schedule.scheduledMonthRepetitions: Int
    get() = monthSchedule[this@Month.asString] ?: throwIllegalMonthException()

context (Month)
val Schedule.monthRepetitionsAreAtLeastNotZero: Boolean
    get() = scheduledMonthRepetitions > 0