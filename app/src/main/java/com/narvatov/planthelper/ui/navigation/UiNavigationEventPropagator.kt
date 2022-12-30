package com.narvatov.planthelper.ui.navigation

import com.narvatov.planthelper.models.data.local.Plant
import kotlinx.coroutines.flow.MutableSharedFlow

object UiNavigationEventPropagator {

    val navigationEvents = MutableSharedFlow<Destination>(extraBufferCapacity = 1)

    fun MutableSharedFlow<Destination>.navigate(destination: Destination) {
        tryEmit(destination)
    }

    fun navigate(destination: Destination) {
        println("NAVIGATOR LOGGER NAVIGATE TO ${destination.route}")
        navigationEvents.navigate(destination)
    }

    fun navigateToPlantDetails(plant: Plant) {
        navigationPlantId = plant.id
        navigate(PlantDetails.withParam(PlantDetails.PLANT_ID_NAV_PARAM, plant.id))
    }

    fun popBack(destination: Destination, inclusive: Boolean) {
        navigationEvents.navigate(Back.withParam(destination, inclusive))
    }

    fun popBack() {
        navigationEvents.navigate(Back)
    }

}