package com.example.planthelper.models.ui.settings

data class SettingsUiState(
    val busyTimeStart: String = "0:00",
    val busyTimeEnd: String = "0:00",
    val currentPlan: String = "Free",
)

fun EmptySettingUiState() = SettingsUiState()
