package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.plant.details.PlantDetailsUiState
import com.example.planthelper.utils.previewPlant

class PlantDetailsViewModel(private val plantId: Long) : ViewModel() {

    var plantDetailsUiState by mutableStateOf(PlantDetailsUiState(previewPlant))
        private set
}