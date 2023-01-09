package com.narvatov.planthelper.data.repository.base.delegate.coroutine

import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.base.delegate.dispatcher.DispatcherDelegate.IODispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object RepositoryCoroutineDelegate : IRepositoryCoroutineDelegate {

    override val repositoryScope = CoroutineScope(IODispatcher + SupervisorJob())

}