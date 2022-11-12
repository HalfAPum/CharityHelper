package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.plant.details.EmptyPlantDetailsUiState

class PlantDetailsViewModel : ViewModel() {

    var plantDetailsUiState by mutableStateOf(EmptyPlantDetailsUiState())
        private set
}