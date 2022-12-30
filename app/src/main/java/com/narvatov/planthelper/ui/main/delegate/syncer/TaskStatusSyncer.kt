package com.narvatov.planthelper.ui.main.delegate.syncer

import androidx.lifecycle.LifecycleOwner
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.task.TaskStatusSyncerRepository
import com.narvatov.planthelper.utils.inject

object TaskStatusSyncer: ITaskStatusSyncer {

    private val taskStatusSyncerRepository: TaskStatusSyncerRepository by inject()

    override fun LifecycleOwner.syncFailedTasks() {
        launchCatching {
            taskStatusSyncerRepository.syncFailedTasks()
        }
    }

}