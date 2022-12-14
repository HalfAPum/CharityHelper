package com.narvatov.planthelper.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
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
import com.narvatov.planthelper.BuildConfig
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import kotlinx.coroutines.flow.Flow
import org.koin.java.KoinJavaComponent.inject
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
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
        val name = "${taskSchedule.scheduleType.name} ${taskSchedule.channelId}"
        val descriptionText = taskSchedule.name
        val importance = NotificationManager.IMPORTANCE_DEFAULT

        val channel = NotificationChannel(taskSchedule.channelId, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        createNotificationChannel(channel)
    }

    notify(id.toInt(), notification)
}