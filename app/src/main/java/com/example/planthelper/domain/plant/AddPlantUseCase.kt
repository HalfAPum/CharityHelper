package com.example.planthelper.domain.plant

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.ui.plant.create.CreatePlantUiState
import org.koin.core.annotation.Factory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val taskRepository: TaskRepository,
) {

    suspend operator fun invoke(createPlantUiState: CreatePlantUiState) {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        val formattedBirthday = sdf.format(createPlantUiState.plantBirthDay)

        val plant = Plant(
            name = createPlantUiState.plantName,
            originName = createPlantUiState.plantType,
            imageUrl = createPlantUiState.imageUrl ?: createPlantUiState.defaultImageUrl,
            age = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(formattedBirthday)),
                YearMonth.from(LocalDate.now())
            ).toInt()
        )

        val plantId = plantRepository.addPlant(plant)
        taskRepository.generateFirstTasksForPlant(plant.copy(id = plantId))
    }

}