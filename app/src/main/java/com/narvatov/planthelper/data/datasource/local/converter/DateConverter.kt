package com.narvatov.planthelper.data.datasource.local.converter

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromLong(time: Long?) = time?.let { Date(time) }

    @TypeConverter
    fun fromDate(date: Date?) = date?.time

}