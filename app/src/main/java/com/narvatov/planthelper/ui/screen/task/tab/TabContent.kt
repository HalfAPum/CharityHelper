package com.narvatov.planthelper.ui.screen.task.tab

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.models.data.local.task.TaskStatus
import com.narvatov.planthelper.models.ui.task.CompositeTask
import com.narvatov.planthelper.models.ui.task.Tab
import com.narvatov.planthelper.ui.theme.LightRed
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.RegularGrey

@Composable
fun TabContent(
    selected: Boolean,
    historyTasks: List<CompositeTask>,
    tab: Tab,
    modifier: Modifier = Modifier,
) {
    val failedTasks = historyTasks.filter { it.task.status == TaskStatus.Failed }.size

    val contentColor = if (selected) RegularBlack else RegularGrey

    Row(
        modifier = modifier
            .padding(bottom = if (selected) 12.dp else 14.dp)
            .height(40.dp)
    ) {
        Image(
            painter = painterResource(tab.icon),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Bottom),
            colorFilter = ColorFilter.tint(contentColor)
        )

        Text(
            text = tab.title,
            fontSize = 16.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = contentColor,
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(start = 8.dp),
        )

        if (tab == Tab.History && failedTasks > 0) {
            Box(
                contentAlignment= Alignment.Center,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .background(LightRed, shape = CircleShape)
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(constraints)

                        val currentHeight = placeable.height
                        val currentWidth = placeable.width
                        val newDiameter = maxOf(currentHeight, currentWidth)

                        layout(newDiameter, newDiameter) {
                            placeable.placeRelative(
                                x = (newDiameter - currentWidth) / 2,
                                y = (newDiameter - currentHeight) / 2)
                        }
                    }
            ) {
                Text(
                    text = failedTasks.toString(),
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = when {
                        failedTasks >= 100 -> Modifier.padding(4.dp)
                        failedTasks >= 10 -> Modifier.padding(2.dp)
                        else -> Modifier
                    }
                )
            }
        }
    }
}