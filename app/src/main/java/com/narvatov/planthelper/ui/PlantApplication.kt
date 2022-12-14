package com.narvatov.planthelper.ui

import android.app.Application
import com.narvatov.planthelper.di.appModule
import com.narvatov.planthelper.di.databaseModule
import com.narvatov.planthelper.di.networkModule
import com.narvatov.planthelper.utils.plantTimberDebug
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule

class PlantApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        plantTimberDebug()

        startKoin {
            androidLogger()
            androidContext(this@PlantApplication)
            workManagerFactory()
            modules(
                defaultModule,
                appModule,
                databaseModule,
                networkModule,
            )
        }
    }
}