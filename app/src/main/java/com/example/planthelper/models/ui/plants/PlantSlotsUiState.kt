package com.example.planthelper.models.ui.plants

import androidx.compose.runtime.Immutable

@Immutable
data class PlantSlotsUiState(
    val plantSlots: List<PlantSlot>
)

fun EmptyPlantSlotsUiState() = PlantSlotsUiState(emptyList())