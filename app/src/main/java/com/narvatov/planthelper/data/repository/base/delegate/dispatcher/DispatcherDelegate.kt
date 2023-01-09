package com.narvatov.planthelper.data.repository.base.delegate.dispatcher

import com.halfapum.general.coroutines.Dispatcher
import com.narvatov.planthelper.utils.inject

object DispatcherDelegate : IDispatcherDelegate {

    override val dispatcher: Dispatcher by inject()

}