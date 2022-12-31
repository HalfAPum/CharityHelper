package com.narvatov.planthelper.data.datasource.local.converter

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {

    @TypeConverter
    fun fromString(uuid: String?) = uuid?.let { UUID.fromString(uuid) }

    @TypeConverter
    fun fromDate(uuid: UUID?) = uuid?.toString()

}