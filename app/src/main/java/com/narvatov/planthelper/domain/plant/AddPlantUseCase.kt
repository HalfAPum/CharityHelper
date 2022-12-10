package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.TaskRepository
import com.narvatov.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val scheduleRepository: ScheduleRepository,
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(createPlantUiState: CreatePlantUiState) {
        val plant = createPlantUiState.transformToPlant()


        scheduleRepository.addSchedulesForPlant(plant)

        val plantId = plantRepository.addPlant(plant)

        taskRepository.generateFirstTasksForPlant(plant.copy(id = plantId))
    }

}