package com.example.planthelper.utils

import com.example.planthelper.models.data.local.schedule.Schedule
import java.text.SimpleDateFormat
import java.util.*

data class Month(val month: Int)

val Month.asString: String
    get() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        val monthDate = SimpleDateFormat("MMMM")
        return monthDate.format(calendar.time)
    }

val currentMonth: Month by lazy {
    val calendar = Calendar.getInstance()
    Month(calendar[Calendar.MONTH])
}

val Month.next: Month
    get() {
        val nextMonth = if (this.month == Calendar.DECEMBER) Month(Calendar.JANUARY)
            else this.copy(month = this.month.plus(1))

        return nextMonth
    }

fun Calendar(month: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month)
    return calendar
}

context (Month)
val Schedule.totalDaysInMonth: Double
    get() = Calendar(month).getActualMaximum(Calendar.DAY_OF_MONTH).toDouble()

context (Month)
val Schedule.monthDay: Int
    get() {
        val monthDay = if (currentMonth.month > month) 0
            else Calendar(month)[Calendar.DAY_OF_MONTH]

        return monthDay
    }

fun forEachMonth(block: Month.() -> Unit) {
    var month = currentMonth
    for (m in Calendar.JANUARY..Calendar.DECEMBER) {
        month.block()

        month = month.next
    }
}

context (Month, Schedule)
fun Double.toDate(): String {
    val day = this.toInt()
    val month = month

    val calendar = Calendar.getInstance().apply {
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
    }

    //TODO CORRECT TO DATE CONVERT TO APPROPRIATE DATE FORMAT WITH !!SDF!!
    return calendar.toString()
}