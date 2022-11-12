package com.example.planthelper.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
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
    version = 1
)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

    abstract fun getTaskDao(): TaskDao

    abstract fun getScheduleDao(): ScheduleDao

}