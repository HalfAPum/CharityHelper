package com.narvatov.planthelper.ui.screen.task.tab

import androidx.compose.material.Tab
import androidx.compose.runtime.Composable
import com.narvatov.planthelper.models.ui.task.Tab

@Composable
fun TaskTab(
    selected: Boolean,
    historyTab: Boolean,
    onClick: () -> Unit,
    onHistoryTabOpened: () -> Unit,
    text: @Composable (() -> Unit)? = null,
) {
    if (selected && historyTab) {
        onHistoryTabOpened()
    }

    Tab(
        selected = selected,
        onClick = onClick,
        text = text,
    )
}