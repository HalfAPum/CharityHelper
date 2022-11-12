package com.example.planthelper.domain

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.models.data.local.Plant
import org.koin.core.annotation.Factory

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(plant: Plant) {
        plantRepository.addPlant(plant)
        taskRepository.generateFirstTasksForPlant(plant)
    }

}