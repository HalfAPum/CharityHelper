package com.example.planthelper.data.repository.base

import com.example.planthelper.utils.Dispatcher
import org.koin.androidx.compose.inject

open class Repository {

    private val dispatcher: Dispatcher by inject()

    protected val IODispatcher = dispatcher.IO
    protected val DefaultDispatcher = dispatcher.Default
    protected val UnconfinedDispatcher = dispatcher.Unconfined

}