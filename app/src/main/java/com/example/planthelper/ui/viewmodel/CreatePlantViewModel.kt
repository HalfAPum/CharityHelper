package com.example.planthelper.ui.viewmodel

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.data.repository.PhotoRepository
import com.example.planthelper.domain.AddPlantUseCase
import com.example.planthelper.models.ui.plant.create.EmptyCreatePlantUiState
import com.halfapum.general.coroutines.launchCatching
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel
import java.util.*

@KoinViewModel
class CreatePlantViewModel(
    private val photoRepository: PhotoRepository,
    private val addPlantUseCase: AddPlantUseCase,
) : ViewModel() {

    private val _savePlantActionSharedFlow: MutableSharedFlow<Unit> = MutableSharedFlow()
    val savePlantActionSharedFlow = _savePlantActionSharedFlow.asSharedFlow()

    var createPlantUiState by mutableStateOf(EmptyCreatePlantUiState())
        private set

    fun saveBitmap(bitmap: Bitmap?) = bitmap?.let {
        val imagePath = photoRepository.saveBitmap(
            oldBitmapPath = createPlantUiState.imageUrl,
            newBitmap = bitmap,
        )

        createPlantUiState = createPlantUiState.copy(imageUrl = imagePath)
    }

    fun updatePlantBirthDay(date: Date) {
        createPlantUiState = createPlantUiState.copy(plantBirthDay = date)
    }

    fun plantNameChanged(plantName: String) {
        createPlantUiState = createPlantUiState.copy(
            plantName = plantName,
            isPlantNameError = false,
        )
    }

    fun savePlant() {
        if (createPlantUiState.isValid) {
            launchCatching {
                addPlantUseCase(createPlantUiState)
                _savePlantActionSharedFlow.emit(Unit)
            }
        } else {
            createPlantUiState = createPlantUiState.copyWithErrors()
        }
    }

}