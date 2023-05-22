package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.json.JSONObject
import retrofit2.HttpException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


abstract class ErrorViewModel: ViewModel() {

    protected val _errorSharedFlow = MutableSharedFlow<String?>(
        replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val errorSharedFlow = _errorSharedFlow.asSharedFlow()

    private val exceptionHandlerEmitting = CoroutineExceptionHandler { _, exception ->
        if (exception is HttpException) {
            val jObjError = exception.response()?.errorBody()?.string()?.let { JSONObject(it) }
            _errorSharedFlow.tryEmit(jObjError?.getString("error"))
        } else {
            _errorSharedFlow.tryEmit(exception.message)
        }
    }

    fun onFieldValueChanged() {
        viewModelScope.launchPrintingError {
            _errorSharedFlow.emit(null)
        }
    }

    protected fun CoroutineScope.launchPrintingError(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job = launch(context + exceptionHandlerEmitting, start, block)
}