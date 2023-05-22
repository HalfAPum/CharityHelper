package com.narvatov.planthelper.utils

import com.narvatov.planthelper.BuildConfig
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber

fun plantTimberDebug() {
    if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }
}

inline fun <reified T> inject() = inject<T>(T::class.java)