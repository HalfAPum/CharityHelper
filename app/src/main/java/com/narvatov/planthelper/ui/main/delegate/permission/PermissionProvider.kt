package com.narvatov.planthelper.ui.main.delegate.permission

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat

object PermissionProvider: IPermissionProvider {

    override fun ComponentActivity.checkNotificationPermission() {
        //TODO REFACTOR PERMOSSIONS

        if (ContextCompat.checkSelfPermission(
                this@ComponentActivity,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission(),
                {},
            ).launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        if (ContextCompat.checkSelfPermission(
                this@ComponentActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission(),
                {},
            ).launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }

}