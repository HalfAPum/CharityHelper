package com.example.planthelper.di

import android.content.Context
import androidx.room.Room
import com.example.planthelper.data.datasource.local.PlantDatabase
import com.example.planthelper.ui.viewmodel.FeedViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {

    fun provideRoomDatabase(context: Context) = Room.databaseBuilder(
        context,
        PlantDatabase::class.java,
        PLANT_DATABASE
    ).build()

    single { provideRoomDatabase(androidContext()) }

    fun providePlantDao(database: PlantDatabase) = database.getPlantDao()

    factory { providePlantDao(get()) }

    fun provideTaskDao(database: PlantDatabase) = database.getTaskDao()

    factory { provideTaskDao(get()) }

    fun provideScheduleDao(database: PlantDatabase) = database.getScheduleDao()

    factory { provideScheduleDao(get()) }

}

private const val PLANT_DATABASE = "PLANT_DATABASE"