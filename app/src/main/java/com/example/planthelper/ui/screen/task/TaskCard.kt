package com.example.planthelper.ui.screen.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.planthelper.models.ui.task.CompositeTask

@Composable
fun TaskCard(
    task: CompositeTask,
    onTaskClicked: (CompositeTask) -> Unit,
    onAcceptClicked: (CompositeTask) -> Unit,
    modifier: Modifier = Modifier
) = with(task) {
    Card(
        modifier = Modifier
            .height(120.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .then(modifier.clickable { onTaskClicked(task) }),
        backgroundColor = Color.White,
    ) {

    }
}