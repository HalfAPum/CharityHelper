package com.narvatov.planthelper.ui.main.delegate.syncer

import androidx.lifecycle.LifecycleOwner
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.TaskRepository
import com.narvatov.planthelper.utils.inject
import org.koin.core.annotation.Single

@Single
object TaskStatusSyncer: ITaskStatusSyncer {

    private val taskRepository: TaskRepository by inject()

    context(LifecycleOwner)
    override fun syncTasks() {
        launchCatching {
            taskRepository.syncTasksStatus()
        }
    }

}