package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory

@Factory
class GetPlantUiStateUseCase(
    private val plantRepository: PlantRepository,
) {

    suspend operator fun invoke(plantId: Long): CreatePlantUiState {
        val plant = plantRepository.getPlant(plantId)

        return CreatePlantUiState(
            plantName = plant.name ?: "",
            plantType = plant.originName,
            defaultImageUrl = plant.imageUrl,
            plantBirthDay = plant.birthdayDate,
            isCreateMode = false,
        )
    }
}