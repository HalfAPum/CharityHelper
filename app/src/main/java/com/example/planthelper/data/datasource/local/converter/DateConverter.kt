package com.example.planthelper.data.datasource.local.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromLong(time: Long) = Date(time)

    @TypeConverter
    fun fromDate(date: Date) = date.time

}