package com.narvatov.planthelper.ui.worker

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.narvatov.planthelper.data.repository.ScheduleRepository
import com.narvatov.planthelper.data.repository.TaskRepository
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.ui.MainActivity
import com.narvatov.planthelper.utils.notify

class NotificationWorker(
    private val taskRepository: TaskRepository,
    private val scheduleRepository: ScheduleRepository,
    context: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val taskId = inputData.getLong(WORKER_TASK_ID, 0L)

        val task = taskRepository.getTask(taskId)

        //If task doesn't exist drop work.
        if (task == null) return Result.failure()
        //If task is completed or failed drop work.
        if (task.status != TaskStatus.Completed) return Result.failure()

        val taskSchedule = scheduleRepository.getSchedule(task.scheduleId)

        //TODO CONFIGURE INTENT LATER
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            PendingIntent.FLAG_MUTABLE
        else PendingIntent.FLAG_ONE_SHOT

        val pendingIntent = PendingIntent.getActivity(applicationContext, 1, intent, pendingIntentFlag)
        //Configure end


        val notification = NotificationCompat
            .Builder(applicationContext, taskSchedule.channelId)
            .run {
                setSmallIcon(taskSchedule.scheduleType.icon)
                setContentTitle(task.name)
                setContentText("Some content text")
                priority = NotificationCompat.PRIORITY_HIGH
                setContentIntent(pendingIntent)
                setAutoCancel(true)
                build()
            }


        NotificationManagerCompat.from(applicationContext)
            .notify(taskSchedule, task.id, notification)

        //TODO plan next notification

        return Result.success()
    }


    companion object {
        const val WORKER_TASK_ID = "WORKER_TASK_ID_INPUT_DATA"
        const val WATERING_CHANNEL_ID = "WATERING_CHANNEL_ID"
        const val FERTILIZER_CHANNEL_ID = "FERTILIZER_CHANNEL_ID"
        const val PRUNING_CHANNEL_ID = "PRUNING_CHANNEL_ID"
    }
}