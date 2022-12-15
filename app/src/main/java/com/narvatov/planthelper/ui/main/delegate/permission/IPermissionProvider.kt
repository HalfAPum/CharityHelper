package com.narvatov.planthelper.ui.main.delegate.permission

import androidx.activity.ComponentActivity

interface IPermissionProvider {

    context(ComponentActivity)
    fun checkNotificationPermission()

}