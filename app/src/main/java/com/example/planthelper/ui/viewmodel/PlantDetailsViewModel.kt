package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.plant.details.PlantDetailsUiState
import com.example.planthelper.utils.previewPlant
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PlantDetailsViewModel : ViewModel() {

    var plantDetailsUiState by mutableStateOf(PlantDetailsUiState(previewPlant))
        private set
}