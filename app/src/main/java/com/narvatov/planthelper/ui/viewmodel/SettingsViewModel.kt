package com.narvatov.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.narvatov.planthelper.models.ui.settings.EmptySettingUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SettingsViewModel : ViewModel() {

    var settingsUiState by mutableStateOf(EmptySettingUiState())
        private set

    fun updateBusyTimeStart(busyTimeStart: String) {
        settingsUiState = settingsUiState.copy(busyTimeStart = busyTimeStart)
    }

    fun updateBusyTimeEnd(busyTimeEnd: String) {
        settingsUiState = settingsUiState.copy(busyTimeEnd = busyTimeEnd)
    }

}