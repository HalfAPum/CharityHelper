package com.example.planthelper.models.data.local.schedule

import androidx.room.ColumnInfo

data class MonthSchedule(
    @ColumnInfo(name = "June")
    val june: Int = 0,
    @ColumnInfo(name = "July")
    val july: Int = 0,
    @ColumnInfo(name = "August")
    val august: Int = 0,
    @ColumnInfo(name = "September")
    val september: Int = 0,
    @ColumnInfo(name = "October")
    val october: Int = 0,
    @ColumnInfo(name = "November")
    val november: Int = 0,
    @ColumnInfo(name = "December")
    val december: Int = 0,
    @ColumnInfo(name = "January")
    val january: Int = 0,
    @ColumnInfo(name = "February")
    val february: Int = 0,
    @ColumnInfo(name = "March")
    val march: Int = 0,
    @ColumnInfo(name = "April")
    val april: Int = 0,
    @ColumnInfo(name = "May")
    val may: Int = 0,
)