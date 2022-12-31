package com.narvatov.planthelper.domain.plant

import android.content.Context
import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.task.TaskGeneratorRepository
import com.narvatov.planthelper.data.repository.task.TaskRepository
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.utils.scheduleNotificationWorkers
import org.koin.core.annotation.Factory

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val scheduleRepository: ScheduleRepository,
    private val taskRepository: TaskRepository,
    private val taskGeneratorRepository: TaskGeneratorRepository,
    private val context: Context,
) {

    suspend operator fun invoke(plant: Plant) {
        scheduleRepository.addSchedulesForPlant(plant)


        val plantId = plantRepository.addPlant(plant)

        taskGeneratorRepository.generateFirstTasksForPlant(plant.copy(id = plantId))
//
//        val tasks = tasksIds.mapNotNull { taskRepository.getTask(it) }
//
//
//        context.scheduleNotificationWorkers(tasks)
    }

}