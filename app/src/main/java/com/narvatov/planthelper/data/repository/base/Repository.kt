package com.narvatov.planthelper.data.repository.base

import android.content.Context
import com.halfapum.general.coroutines.Dispatcher
import com.halfapum.general.coroutines.exception.generalCoroutineExceptionHandler
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.utils.inject
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

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

    fun Repository.launchCatching(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = repositoryScope.launchCatching(context, start, block)

}