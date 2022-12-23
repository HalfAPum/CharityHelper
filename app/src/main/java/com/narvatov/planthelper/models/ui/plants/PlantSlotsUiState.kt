package com.narvatov.planthelper.models.ui.plants

import androidx.compose.runtime.Immutable
import com.narvatov.planthelper.models.ui.plants.slot.PlantSlot

@Immutable
data class PlantSlotsUiState(
    val plantSlots: List<PlantSlot>
)

//TODO replace emptyList with empty and locked slots
fun EmptyPlantSlotsUiState() = PlantSlotsUiState(emptyList())