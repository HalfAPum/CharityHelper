package com.narvatov.planthelper.di

import androidx.room.Room
import com.narvatov.planthelper.data.datasource.local.PlantDatabase
import com.narvatov.planthelper.data.datasource.local.TransactionManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            PlantDatabase::class.java,
            PLANT_DATABASE
        //TODO REMOVE DESTRUCTIVE MIGRATIONS
        ).fallbackToDestructiveMigration().build()
    }

    fun provideTransactionManager(
        plantDatabase: PlantDatabase
    ): TransactionManager = plantDatabase

    factory { provideTransactionManager(get()) }

    fun providePlantDao(database: PlantDatabase) = database.getPlantDao()

    factory { providePlantDao(get()) }

    fun provideTaskDao(database: PlantDatabase) = database.getTaskDao()

    factory { provideTaskDao(get()) }

    fun provideScheduleDao(database: PlantDatabase) = database.getScheduleDao()

    factory { provideScheduleDao(get()) }

}

private const val PLANT_DATABASE = "PLANT_DATABASE"