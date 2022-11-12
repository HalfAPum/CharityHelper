package com.example.planthelper.utils

import com.example.planthelper.BuildConfig
import timber.log.Timber

fun plantTimberDebug() {
    if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }
}