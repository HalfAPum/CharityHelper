package com.narvatov.planthelper.ui.viewmodel.plant.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.models.ui.plant.details.EmptyPlantDetailsUiState
import com.narvatov.planthelper.models.ui.plant.details.PlantDetailsUiState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class PlantDetailsViewModel(
    private val plantId: Long,
    private val plantRepository: PlantRepository,
) : ViewModel() {

    var plantDetailsUiState by mutableStateOf(EmptyPlantDetailsUiState())
        private set

    init { collectLoadPlantFlow() }

    private fun collectLoadPlantFlow() {
        plantRepository.plantFlow(plantId)
            .onEach { plantDetailsUiState = PlantDetailsUiState(it) }
            .launchIn(viewModelScope)
    }

}