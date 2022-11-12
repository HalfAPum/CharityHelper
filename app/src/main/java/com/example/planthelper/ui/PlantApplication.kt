package com.example.planthelper.ui

import android.app.Application
import com.example.planthelper.di.appModule
import com.example.planthelper.di.databaseModule
import com.example.planthelper.di.networkModule
import com.example.planthelper.di.viewModelModule
import com.example.planthelper.utils.plantTimberDebug
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plantTimberDebug()

        startKoin {
            androidLogger()
            androidContext(this@PlantApplication)
            modules(appModule, databaseModule, networkModule, viewModelModule)
        }

    }
}