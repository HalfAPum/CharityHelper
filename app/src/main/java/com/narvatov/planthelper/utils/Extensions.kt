package com.narvatov.planthelper.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.narvatov.planthelper.BuildConfig
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import com.narvatov.planthelper.models.data.local.task.Task
import com.narvatov.planthelper.ui.main.MainActivity
import com.narvatov.planthelper.ui.worker.NotificationWorker
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun plantTimberDebug() {
    if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
    }
}

inline fun <reified T> inject() = inject<T>(T::class.java)

fun CharSequence?.isNotBlank(): Boolean = this?.isBlank() == false

val Date?.shortBirthDay: String
    get() {
        val sdf = SimpleDateFormat("MMM yyyy")
        val shortValue = this?.let { sdf.format(it) }
        return shortValue ?: ""
    }

@Composable
fun <T> rememberFlow(
    flow: Flow<T>,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
): Flow<T> {
    return remember(key1 = flow, key2 = lifecycleOwner) { flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED) }
}

@Composable
fun <T : R, R> Flow<T>.collectAsStateLifecycleAware(
    initial: R,
    context: CoroutineContext = EmptyCoroutineContext
): State<R> {
    val lifecycleAwareFlow = rememberFlow(flow = this)
    return lifecycleAwareFlow.collectAsState(initial = initial, context = context)
}

fun NotificationManagerCompat.notify(
    taskSchedule: Schedule,
    id: Long,
    notification: Notification
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "${taskSchedule.scheduleType.name} ${taskSchedule.notificationChannelId}"
        val descriptionText = taskSchedule.name
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(taskSchedule.notificationChannelId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        createNotificationChannel(channel)
    }

    notify(id.toInt(), notification)
}

context (Repository)
fun Task.scheduleNotification(): Task {
    val workRequests = OneTimeWorkRequestBuilder<NotificationWorker>().run {
        val inputData = Data.Builder().putLong(NotificationWorker.WORKER_TASK_ID, id).build()
        setInputData(inputData)
        val initialDelay = scheduledDate.time - System.currentTimeMillis()
        setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
        build()
    }

    WorkManager.getInstance(applicationContext).enqueue(workRequests)

    return copy(notificationId = workRequests.id)
}

context (Repository)
fun Task.cancelScheduledNotifications() {
    notificationId?.let {
        WorkManager.getInstance(applicationContext).cancelWorkById(notificationId)
    }
}

context (Repository)
fun List<Task>.cancelScheduledNotifications() {
    forEach { it.cancelScheduledNotifications() }
}

fun Context.getSingleActivityPendingIntent(): PendingIntent {
    val intent = Intent(this, MainActivity::class.java)
    val pendingIntentFlag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
        PendingIntent.FLAG_IMMUTABLE
    else PendingIntent.FLAG_ONE_SHOT

    return PendingIntent.getActivity(this, 1, intent, pendingIntentFlag)
}

fun logSeparator() = Timber.d("------------------")