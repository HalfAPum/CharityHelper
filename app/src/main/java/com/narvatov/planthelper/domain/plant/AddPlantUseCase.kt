package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.task.TaskGeneratorRepository
import com.narvatov.planthelper.models.data.local.Plant
import org.koin.core.annotation.Factory

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val scheduleRepository: ScheduleRepository,
    private val taskGeneratorRepository: TaskGeneratorRepository,
) {

    suspend operator fun invoke(plant: Plant) {
        scheduleRepository.addSchedulesForPlant(plant)

        val plantId = plantRepository.addPlant(plant)

        taskGeneratorRepository.generateFirstTasksForPlant(plant.copy(id = plantId))
    }

}