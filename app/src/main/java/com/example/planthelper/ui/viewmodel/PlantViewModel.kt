package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planthelper.domain.PlantSlotFlowUseCase
import com.example.planthelper.models.ui.plants.EmptyPlantSlotsUiState
import com.example.planthelper.models.ui.plants.PlantSlotsUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PlantViewModel(private val plantSlotFlowUseCase: PlantSlotFlowUseCase) : ViewModel() {

    var plantSlotsUiState by mutableStateOf(EmptyPlantSlotsUiState())
        private set

    init { collectLoadPlantsFlow() }

    private fun collectLoadPlantsFlow() {
        plantSlotFlowUseCase()
            .onEach { plantSlotsUiState = PlantSlotsUiState(it) }
            .launchIn(viewModelScope)
    }
}