package com.narvatov.planthelper.ui.viewmodel.plant.create

import android.graphics.Bitmap
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.PhotoRepository
import com.narvatov.planthelper.data.repository.plant.PlantInfoRepository
import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.domain.plant.GetPlantUiStateUseCase
import com.narvatov.planthelper.domain.plant.SavePlantUseCase
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.ui.plant.create.EmptyCreatePlantUiState
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import org.koin.android.annotation.KoinViewModel
import java.util.*

@KoinViewModel
class CreatePlantViewModel(
    private val plantId: Long?,
    private val plantRepository: PlantRepository,
    private val plantInfoRepository: PlantInfoRepository,
    private val savePlantUseCase: SavePlantUseCase,
    private val getPlantUiStateUseCase: GetPlantUiStateUseCase,
) : ViewModel() {

    var createPlantUiState by mutableStateOf(EmptyCreatePlantUiState())
        private set

    init {
        //Preload plant types
        launchCatching { plantInfoRepository.loadPlantTypes() }

        launchCatching {
            plantId?.let {
                createPlantUiState = getPlantUiStateUseCase(plantId)
            }
        }
    }

    fun updatePlantImage(bitmap: Bitmap?) = bitmap?.let {
        createPlantUiState = createPlantUiState.copy(imageBitmap = bitmap)
    }

    fun updatePlantBirthDay(date: Date) {
        if (date.time > Date().time) return

        createPlantUiState = createPlantUiState.copy(plantBirthDay = date)
    }

    fun plantNameChanged(plantName: String) {
        createPlantUiState = createPlantUiState.copy(
            plantName = plantName,
            isPlantNameError = false,
        )
    }

    fun savePlant() {
        launchCatching {
            if (createPlantUiState.isValid) {
                savePlantUseCase(createPlantUiState, plantId)
                popBack()
            } else {
                createPlantUiState = createPlantUiState.copyWithErrors()
            }
        }
    }

    fun deletePlant() {
        launchCatching {
            plantId?.let { plantRepository.deletePlant(plantId) }
            popBack(destination = BottomNavigation.Plants, inclusive = false)
        }
    }

    fun plantTypeSelected(plant: Plant) {
        createPlantUiState = createPlantUiState.copy(
            plantType = plant.originName,
            defaultImageUrl = plant.imageUrl,
        )
    }

}