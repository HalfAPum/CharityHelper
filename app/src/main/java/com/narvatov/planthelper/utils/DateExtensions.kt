package com.narvatov.planthelper.utils

import com.narvatov.planthelper.models.data.local.schedule.Schedule
import java.text.SimpleDateFormat
import java.util.*

data class MonthYear(val month: Int, val year: Int)

val MonthYear.asString: String
    get() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
        val monthDate = SimpleDateFormat("MMMM")
        return monthDate.format(calendar.time)
    }

val currentMonth: MonthYear by lazy {
    val calendar = Calendar.getInstance()
    MonthYear(calendar[Calendar.MONTH], calendar[Calendar.YEAR])
}

val MonthYear.next: MonthYear
    get() {
        val nextMonth = if (this.month == Calendar.DECEMBER) MonthYear(Calendar.JANUARY, this.year.plus(1))
            else this.copy(month = this.month.plus(1))

        return nextMonth
    }

fun Calendar(month: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, month)
    return calendar
}

context (MonthYear)
val Schedule.totalDaysInMonth: Double
    get() = Calendar(month).getActualMaximum(Calendar.DAY_OF_MONTH).toDouble()

context (MonthYear)
val Schedule.monthDay: Int
    get() {
        val monthDay = if (currentMonth.month > month) 0
            else Calendar(month)[Calendar.DAY_OF_MONTH]

        return monthDay
    }

inline fun forEachMonth(block: MonthYear.() -> Unit) {
    var month = currentMonth
    for (m in Calendar.JANUARY..Calendar.DECEMBER) {
        month.block()

        month = month.next
    }
}

context (MonthYear, Schedule)
fun Double.toDate(): Date {
    val day = this.toInt()

    val calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, year)
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
    }

    return calendar.time
}