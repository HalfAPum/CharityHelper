package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.plant.create.CreatePlantUiState
import com.example.planthelper.models.ui.plant.create.EmptyCreatePlantUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreatePlantViewModel : ViewModel() {

    var createPlantUiState by mutableStateOf(EmptyCreatePlantUiState())
        private set

}