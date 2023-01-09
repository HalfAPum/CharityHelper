package com.narvatov.planthelper.data.repository.base

import android.content.Context
import com.halfapum.general.coroutines.Dispatcher
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.base.delegate.context.ApplicationContextDelegate
import com.narvatov.planthelper.data.repository.base.delegate.context.IApplicationContextDelegate
import com.narvatov.planthelper.data.repository.base.delegate.coroutine.IRepositoryCoroutineDelegate
import com.narvatov.planthelper.data.repository.base.delegate.coroutine.RepositoryCoroutineDelegate
import com.narvatov.planthelper.data.repository.base.delegate.dispatcher.DispatcherDelegate
import com.narvatov.planthelper.data.repository.base.delegate.dispatcher.IDispatcherDelegate
import com.narvatov.planthelper.utils.inject
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

open class Repository : IApplicationContextDelegate by ApplicationContextDelegate,
    IDispatcherDelegate by DispatcherDelegate,
    IRepositoryCoroutineDelegate by RepositoryCoroutineDelegate