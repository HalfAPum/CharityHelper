package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.SettingsRepository
import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.models.data.local.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class AvailablePlantFlowUseCase(
    private val plantRepository: PlantRepository,
    private val settingsRepository: SettingsRepository,
) {

    operator fun invoke(): Flow<List<Plant>> {
        return plantRepository.plantsFlow().map { plants ->
            val availablePlantSlots = settingsRepository.availableSlots

            plants.take(availablePlantSlots)
        }
    }

}