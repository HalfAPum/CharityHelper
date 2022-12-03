package com.example.planthelper.models.ui.plant.details

import com.example.planthelper.models.data.local.Plant

data class PlantDetailsUiState(
    val plant: Plant
)

fun EmptyPlantDetailsUiState() = PlantDetailsUiState(
    plant = Plant(
        originName = "",
        imageUrl = "",
    )
)