package com.narvatov.planthelper.data.repository.base

import android.content.Context
import com.halfapum.general.coroutines.Dispatcher
import com.narvatov.planthelper.utils.inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext

open class Repository {

    val applicationContext: Context by inject()

    private val dispatcher: Dispatcher by inject()

    private val IODispatcher = dispatcher.IO
    private val DefaultDispatcher = dispatcher.Default
    private val UnconfinedDispatcher = dispatcher.Unconfined

    protected suspend fun <T> IOOperation(
        block: suspend CoroutineScope.() -> T
    ) = withContext(IODispatcher) { block() }

    val repositoryScope = CoroutineScope(IODispatcher + SupervisorJob())

}