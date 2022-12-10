package com.narvatov.planthelper.models.ui.plant.details

import com.narvatov.planthelper.models.data.local.Plant

data class PlantDetailsUiState(
    val plant: Plant
)

fun EmptyPlantDetailsUiState() = PlantDetailsUiState(
    plant = Plant(
        originName = "",
        imageUrl = "",
    )
)