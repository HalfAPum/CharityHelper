package com.example.planthelper.domain

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.ScheduleRepository
import com.example.planthelper.data.repository.TaskRepository
import com.example.planthelper.models.ui.task.CompositeTask
import com.example.planthelper.utils.previewPlant
import com.example.planthelper.utils.previewSchedule
import com.example.planthelper.utils.previewTask
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class CompositeTaskFlowUseCase(
    private val plantRepository: PlantRepository,
    private val taskRepository: TaskRepository,
    private val scheduleRepository: ScheduleRepository,
) {

    operator fun invoke() = taskRepository.taskFlow().map { taskList ->
//        taskList.map { task ->
//            val schedule = scheduleRepository.getSchedule(task.scheduleId)
//            val plant = plantRepository.getPlant(schedule.plantId)
//
//            CompositeTask(
//                plant = plant,
//                task = task,
//                schedule = schedule
//            )
//        }

        //TODO REMOVE STUB
        testData
    }

}

private val testData = listOf(
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
    CompositeTask(
        plant = previewPlant,
        task = previewTask,
        schedule = previewSchedule,
    ),
)