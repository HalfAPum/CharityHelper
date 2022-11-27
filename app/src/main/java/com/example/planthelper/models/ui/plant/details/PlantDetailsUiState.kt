package com.example.planthelper.models.ui.plant.details

import com.example.planthelper.models.data.local.Plant

data class PlantDetailsUiState(
    val plant: Plant
)

fun EmptyPlantDetailsUiState() = PlantDetailsUiState(
    plant = Plant(
        name = "",
        originName = "",
        imageUrl = "",
        age = 0,
        health = 0.0,
        id = 0,
    )
)