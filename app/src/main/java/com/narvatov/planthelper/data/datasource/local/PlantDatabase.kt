package com.narvatov.planthelper.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.withTransaction
import com.narvatov.planthelper.data.datasource.local.converter.DateConverter
import com.narvatov.planthelper.data.datasource.local.converter.IntMapConverter
import com.narvatov.planthelper.data.datasource.local.converter.UUIDConverter
import com.narvatov.planthelper.data.datasource.local.dao.BillingDao
import com.narvatov.planthelper.data.datasource.local.dao.PlantDao
import com.narvatov.planthelper.data.datasource.local.dao.ScheduleDao
import com.narvatov.planthelper.data.datasource.local.dao.TaskDao
import com.narvatov.planthelper.models.data.local.BillingSubscription
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task

@Database(
    entities = [
        Plant::class,
        Task::class,
        Schedule::class,
        BillingSubscription::class,
    ],
    version = DB_VERSION,
)
@TypeConverters(IntMapConverter::class, DateConverter::class, UUIDConverter::class)
abstract class PlantDatabase : RoomDatabase(), TransactionManager {

    override suspend fun <R> withTransaction(block: suspend () -> R): R {
        return (this as RoomDatabase).withTransaction(block)
    }

    abstract fun getPlantDao(): PlantDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getScheduleDao(): ScheduleDao

    abstract fun getBillingDao(): BillingDao

}

private const val DB_VERSION = 10