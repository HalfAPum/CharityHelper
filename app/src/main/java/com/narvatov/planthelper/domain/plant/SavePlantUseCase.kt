package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory

@Factory
class SavePlantUseCase(
    private val plantRepository: PlantRepository,
    private val addPlantUseCase: AddPlantUseCase,
) {

    suspend operator fun invoke(createPlantUiState: CreatePlantUiState) {
        val plant = createPlantUiState.transformToPlant()

        if (createPlantUiState.isCreateMode) {
            addPlantUseCase(plant)
        } else {
            plantRepository.updatePlant(plant)
        }
    }
}