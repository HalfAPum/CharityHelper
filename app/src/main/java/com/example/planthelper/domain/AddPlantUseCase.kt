package com.example.planthelper.domain

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(createPlantUiState: CreatePlantUiState) {
        val plant = Plant(
            name = createPlantUiState.plantName,
            originName = createPlantUiState.plantType,
            imageUrl = createPlantUiState.imageUrl ?: createPlantUiState.defaultImageUrl,
            age = ChronoUnit.MONTHS.between(LocalDate.parse(createPlantUiState.plantBirthDay.toString()), LocalDate.now()).toInt()
        )

        println("FUCK $plant")
//        plantRepository.addPlant(plant)
//        taskRepository.generateFirstTasksForPlant(plant)
    }

}