package com.narvatov.planthelper.domain.task

import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.task.TaskRepository
import com.narvatov.planthelper.models.ui.task.CompositeTask
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class CompositeTaskFlowUseCase(
    private val plantRepository: PlantRepository,
    private val taskRepository: TaskRepository,
    private val scheduleRepository: ScheduleRepository,
) {

    operator fun invoke(plantId: Long?) = taskRepository.taskFlow(plantId).map { taskList ->
        taskList.map { task ->
            val plant = plantRepository.getPlant(task.plantId)
            val schedule = scheduleRepository.getSchedule(task.scheduleId)

            CompositeTask(
                plant = plant,
                task = task,
                schedule = schedule
            )
        }
    }

}