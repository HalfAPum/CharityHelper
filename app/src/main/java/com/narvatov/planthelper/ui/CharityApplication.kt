package com.narvatov.planthelper.ui

import android.app.Application
import android.util.Log
import com.halfapum.general.coroutines.exception.DefaultCoroutineExceptionHandler
import com.halfapum.general.coroutines.exception.ExceptionPropagator
import com.halfapum.general.coroutines.exception.generalCoroutineExceptionHandler
import com.narvatov.planthelper.di.appModule
import com.narvatov.planthelper.di.networkModule
import com.narvatov.planthelper.utils.plantTimberDebug
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.ksp.generated.defaultModule
import kotlin.coroutines.CoroutineContext

class CharityApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        generalCoroutineExceptionHandler = object : DefaultCoroutineExceptionHandler() {
            override fun handleException(context: CoroutineContext, exception: Throwable) {
                Log.e(coroutineTag, exceptionMessage, exception)

                ExceptionPropagator.propagate(exception)
            }
        }

        plantTimberDebug()

        startKoin {
            androidLogger()
            androidContext(this@CharityApplication)
            modules(
                defaultModule,
                appModule,
                networkModule,
            )
        }
    }
}