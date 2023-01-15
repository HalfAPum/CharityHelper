package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.PhotoRepository
import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory

@Factory
class SavePlantUseCase(
    private val photoRepository: PhotoRepository,
    private val addPlantUseCase: AddPlantUseCase,
    private val plantRepository: PlantRepository,
) {

    suspend operator fun invoke(
        createPlantUiState: CreatePlantUiState,
        plantId: Long? = null
    ) {
        val oldPlant = plantId?.let { plantRepository.getPlant(it) }

        val imagePath = photoRepository.savePlantImage(
            photo = createPlantUiState.imageBitmap,
            defaultImage = createPlantUiState.defaultImageUrl,
            oldPhotoPath = oldPlant?.imageUrl,
        )

        val plant = createPlantUiState.transformToPlant(
            id = oldPlant?.id,
            imagePath = imagePath,
        )

        if (createPlantUiState.isCreateMode) {
            addPlantUseCase(plant)
        } else {
            plantRepository.updatePlant(plant)
        }
    }
}