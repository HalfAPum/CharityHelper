package com.narvatov.planthelper.ui.navigation

import kotlinx.coroutines.flow.MutableSharedFlow

object UiNavigationEventPropagator {

    val navigationEvents = MutableSharedFlow<Destination>()

    fun MutableSharedFlow<Destination>.navigate(destination: Destination) {
        tryEmit(destination)
    }

}