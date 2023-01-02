package com.narvatov.planthelper.ui.navigation

import com.narvatov.planthelper.models.data.local.Plant
import kotlinx.coroutines.flow.MutableSharedFlow

object UiNavigationEventPropagator {

    val navigationEvents = MutableSharedFlow<Destination>(extraBufferCapacity = 1)

    private fun MutableSharedFlow<Destination>.navigate(destination: Destination) {
        println("NAVIGATOR LOGGER NAVIGATE TO ${destination.route}")
        tryEmit(destination)
    }

    fun navigate(destination: Destination) {
        navigationEvents.navigate(destination)
    }

    fun navigateToPlantDetails(plant: Plant) {
        navigationPlantId = plant.id
        navigate(PlantDetails.withParam(PlantDetails.PLANT_ID_NAV_PARAM, plant.id))
    }

    fun navigateToEditPlant(plant: Plant) {
        navigationEditPlantId = plant.id
        navigate(CreatePlant)
    }

    fun popBack(destination: Destination, inclusive: Boolean = false) {
        navigationEvents.navigate(Back.withParam(destination, inclusive))
    }

    fun popBack() {
        navigationEvents.navigate(Back)
    }

}