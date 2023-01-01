package com.narvatov.planthelper.ui.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.task.TaskGeneratorRepository
import com.narvatov.planthelper.data.repository.task.TaskRepository
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.utils.*

class NotificationWorker(
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    private val taskRepository: TaskRepository by inject()
    private val scheduleRepository: ScheduleRepository by inject()
    private val plantRepository: PlantRepository by inject()
    private val taskGeneratorRepository: TaskGeneratorRepository by inject()

    override suspend fun doWork(): Result {
        val taskId = inputData.getLong(WORKER_TASK_ID, 0L)

        val task = taskRepository.getTask(taskId)

        //If task doesn't exist drop work.
        if (task == null) return Result.failure()
        //If task is not scheduled drop work.
        if (task.status != TaskStatus.Scheduled) return Result.failure()

        val taskSchedule = scheduleRepository.getSchedule(task.scheduleId)
        val plant = plantRepository.getPlant(task.plantId)

        val title = taskSchedule.scheduleType.action
        val contentText = "${plant.plantName} (${plant.originName}) needs" +
                " ${taskSchedule.scheduleType.action}. ${taskSchedule.name ?: ""}"

        val notification = NotificationCompat
            .Builder(applicationContext, taskSchedule.notificationChannelId)
            .run {
                setSmallIcon(taskSchedule.scheduleType.icon)
                setContentTitle(title)
                setContentText(contentText)

                priority = NotificationCompat.PRIORITY_HIGH
                setContentIntent(applicationContext.getSingleActivityPendingIntent())
                setAutoCancel(true)
                build()
            }


        NotificationManagerCompat.from(applicationContext)
            .notify(taskSchedule, task.id, notification)


        taskRepository.updateTaskStatus(task, TaskStatus.Active)

        taskGeneratorRepository.generateNextTask(task)

        return Result.success()
    }


    companion object {
        const val WORKER_TASK_ID = "WORKER_TASK_ID_INPUT_DATA"
    }
}