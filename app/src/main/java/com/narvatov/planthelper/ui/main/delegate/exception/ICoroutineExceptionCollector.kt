package com.narvatov.planthelper.ui.main.delegate.exception

import androidx.activity.ComponentActivity

interface ICoroutineExceptionCollector {

    context(ComponentActivity)
    fun initCoroutineExceptionCollector()

}