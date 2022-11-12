package com.example.planthelper.di

import android.content.Context
import androidx.room.Room
import com.example.planthelper.data.datasource.local.PlantDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun provideRoomDatabase(context: Context) = Room.databaseBuilder(
        context,
        PlantDatabase::class.java,
        PLANT_DATABASE
    ).build()

    fun providePlantDao(database: PlantDatabase) = database.getPlantDao()

    single { provideRoomDatabase(androidContext()) }

    factory { providePlantDao(get()) }

}

private const val PLANT_DATABASE = "PLANT_DATABASE"