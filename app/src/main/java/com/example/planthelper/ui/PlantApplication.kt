package com.example.planthelper.ui

import android.app.Application
import com.example.planthelper.BuildConfig
import com.example.planthelper.di.appModule
import com.example.planthelper.di.databaseModule
import com.example.planthelper.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class PlantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PlantApplication)
            modules(appModule, databaseModule, viewModelModule)
        }

    }
}