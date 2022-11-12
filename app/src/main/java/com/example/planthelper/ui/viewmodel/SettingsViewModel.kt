package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.settings.EmptySettingUiState

class SettingsViewModel : ViewModel() {

    var settingsUiState by mutableStateOf(EmptySettingUiState())
        private set

}