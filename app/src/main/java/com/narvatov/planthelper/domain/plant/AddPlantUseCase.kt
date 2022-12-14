package com.narvatov.planthelper.domain.plant

import android.content.Context
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.narvatov.planthelper.data.repository.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.TaskRepository
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.worker.NotificationWorker
import com.narvatov.planthelper.ui.worker.NotificationWorker.Companion.WORKER_TASK_ID
import org.koin.core.annotation.Factory

@Factory
class AddPlantUseCase(
    private val plantRepository: PlantRepository,
    private val scheduleRepository: ScheduleRepository,
    private val taskRepository: TaskRepository,
    private val context: Context,
) {

    suspend operator fun invoke(plant: Plant) {
        scheduleRepository.addSchedulesForPlant(plant)

        val plantId = plantRepository.addPlant(plant)

        val tasks = taskRepository.generateFirstTasksForPlant(plant.copy(id = plantId))


        val workRequests = tasks.map { taskId ->
            OneTimeWorkRequestBuilder<NotificationWorker>().run {
                val inputData = Data.Builder().putLong(WORKER_TASK_ID, taskId).build()

                setInputData(inputData)
                //TODO SETUP CORRECT DELAY
//                setInitialDelay()
                build()
            }
        }

        WorkManager.getInstance(context).enqueue(workRequests)
    }

}