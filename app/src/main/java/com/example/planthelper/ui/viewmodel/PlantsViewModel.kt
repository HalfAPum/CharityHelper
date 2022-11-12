package com.example.planthelper.ui.viewmodel

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.plants.EmptyPlantSlotsUiState
import com.example.planthelper.models.ui.plants.PlantSlot
import com.example.planthelper.models.ui.plants.PlantSlot.FilledSlot
import com.example.planthelper.models.ui.plants.PlantSlotsUiState

class PlantsViewModel : ViewModel() {

    val columns = GridCells.Fixed(PLANT_COLUMNS)

    var plantSlotsUiState by mutableStateOf(EmptyPlantSlotsUiState())
        private set

    init {
        loadPlantSlots()
    }

    private fun loadPlantSlots() {
        plantSlotsUiState = plantSlotsUiState.copy(plantSlots = testData)
    }

    fun removePlant(filledSlot: FilledSlot) {

    }

    companion object {
        private const val PLANT_COLUMNS = 2
    }
}

private val testData = listOf(
    PlantSlot.FilledSlot("plant1"),
    PlantSlot.FilledSlot("plant2"),
    PlantSlot.FilledSlot("plant3"),
    PlantSlot.EmptySlot,
    PlantSlot.EmptySlot,
    PlantSlot.EmptySlot,
    PlantSlot.LockedSlot,
    PlantSlot.LockedSlot,
    PlantSlot.LockedSlot,
    PlantSlot.LockedSlot,
)