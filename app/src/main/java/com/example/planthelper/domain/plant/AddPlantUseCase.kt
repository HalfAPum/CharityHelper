package com.example.planthelper.domain.plant

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.ScheduleRepository
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.models.ui.plant.create.CreatePlantUiState
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