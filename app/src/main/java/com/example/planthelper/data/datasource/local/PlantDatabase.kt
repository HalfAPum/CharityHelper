package com.example.planthelper.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.example.planthelper.data.datasource.local.converter.DateConverter
import com.example.planthelper.data.datasource.local.converter.IntMapConverter
import com.example.planthelper.data.datasource.local.dao.PlantDao
import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.datasource.local.dao.TaskDao
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.task.Task

@Database(
    entities = [
        Plant::class,
        Task::class,
        Schedule::class,
    ],
    version = DB_VERSION,
)
@TypeConverters(IntMapConverter::class, DateConverter::class)
abstract class PlantDatabase : RoomDatabase(), TransactionManager {

    override suspend fun <R> withTransaction(block: suspend () -> R): R {
        return (this as RoomDatabase).withTransaction(block)
    }

    abstract fun getPlantDao(): PlantDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getScheduleDao(): ScheduleDao

}

private const val DB_VERSION = 6