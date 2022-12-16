package com.narvatov.planthelper.ui.main.delegate.syncer

import androidx.lifecycle.LifecycleOwner

interface ITaskStatusSyncer {

    context(LifecycleOwner)
    fun syncTasks()

}