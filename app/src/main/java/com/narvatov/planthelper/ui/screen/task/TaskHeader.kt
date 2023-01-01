package com.narvatov.planthelper.ui.screen.task

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.models.ui.task.TaskUIElement
import com.narvatov.planthelper.ui.theme.RegularBlack

@Composable
fun TaskHeader(header: TaskUIElement.Header) {
    Text(
        text = header.text,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = RegularBlack
    )
}