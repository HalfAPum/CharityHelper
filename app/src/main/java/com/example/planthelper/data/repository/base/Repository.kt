package com.example.planthelper.data.repository.base

import com.example.planthelper.utils.Dispatcher
import com.example.planthelper.utils.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

open class Repository {

    private val dispatcher: Dispatcher by inject()

    protected val IODispatcher = dispatcher.IO
    protected val DefaultDispatcher = dispatcher.Default
    protected val UnconfinedDispatcher = dispatcher.Unconfined

    protected suspend fun <T> IOOperation(
        block: suspend CoroutineScope.() -> T
    ) = withContext(IODispatcher) { block() }

}