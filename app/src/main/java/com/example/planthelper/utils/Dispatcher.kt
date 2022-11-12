package com.example.planthelper.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import org.koin.core.annotation.Single

@OptIn(DelicateCoroutinesApi::class)
data class Dispatcher(
    val Main: CoroutineDispatcher = Dispatchers.Main,
    val IO: CoroutineDispatcher = Dispatchers.IO,
    val Default: CoroutineDispatcher = Dispatchers.Main,
    val Unconfined: CoroutineDispatcher = Dispatchers.Unconfined,
    private val NewThread: CoroutineDispatcher? = null
) {

    fun getNewThread(name: String) = NewThread ?: newSingleThreadContext(name)

}