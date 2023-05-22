package com.narvatov.planthelper.ui.screen.proposal

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CheckChoise(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    label: String,
    enabled: Boolean = true,
    modifier: Modifier? = null
) {
    Row(modifier = modifier ?: Modifier.padding(top = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = if (enabled)onCheckedChange else { b -> },
        )
        Text(text = label)
    }
}