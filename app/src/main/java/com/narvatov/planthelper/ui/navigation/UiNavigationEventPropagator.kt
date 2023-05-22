package com.narvatov.planthelper.ui.navigation

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

object UiNavigationEventPropagator {

    val navigationEvents = MutableSharedFlow<Destination>(extraBufferCapacity = 10)

    private fun MutableSharedFlow<Destination>.navigate(destination: Destination) {
        println("NAVIGATOR LOGGER NAVIGATE TO ${destination.route}")
        GlobalScope.launch { emit(destination) }
    }

    fun navigate(destination: Destination) {
        navigationEvents.navigate(destination)
    }

    fun popBack(destination: Destination, inclusive: Boolean = false) {
        navigationEvents.navigate(Back.withParam(destination, inclusive))
    }

    fun popBack() {
        navigationEvents.navigate(Back)
    }

    fun publishToaster(message: String) {
        navigationEvents.navigate(Toast(message))
    }

}