package com.example.planthelper.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planthelper.data.datasource.local.dao.PlantDao
import com.example.planthelper.models.data.local.Plant

@Database(
    entities = [
        Plant::class
    ],
    version = 1
)
abstract class PlantDatabase : RoomDatabase() {

    abstract fun getPlantDao(): PlantDao

}