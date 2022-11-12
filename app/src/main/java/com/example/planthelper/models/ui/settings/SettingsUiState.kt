package com.example.planthelper.models.ui.settings

data class SettingsUiState(
    val isLoggedIn: Boolean
)

fun EmptySettingUiState() = SettingsUiState(false)
