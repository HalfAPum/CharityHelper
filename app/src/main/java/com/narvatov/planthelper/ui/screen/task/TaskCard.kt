package com.narvatov.planthelper.ui.screen.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.data.local.schedule.healthPlusPercentage
import com.narvatov.planthelper.models.ui.task.TaskUIElement
import com.narvatov.planthelper.ui.getColor
import com.narvatov.planthelper.ui.theme.healthColorMap
import com.narvatov.planthelper.ui.theme.healthGood

@Composable
fun TaskCard(
    compositeTask: TaskUIElement.CompositeTask,
    onTaskClicked: (TaskUIElement.CompositeTask) -> Unit,
    onAcceptClicked: (TaskUIElement.CompositeTask) -> Unit,
    modifier: Modifier = Modifier
) = with(compositeTask) {
    Card(modifier = Modifier
            .height(120.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .then(modifier.clickable { onTaskClicked(compositeTask) })
            .background(color = Color.White),
    ) {
        Column(modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .crossfade(true)
                        .data(plant.imageUrl)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(32.dp).clip(CircleShape)
                )

                Text(
                    text = plant.plantName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )

                Text(
                    text = plant.healthPercents,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 4.dp),
                    color = healthColorMap.getColor(plant.health)
                )

                Spacer(modifier = modifier.weight(1f))

                Text(
                    text = task.scheduledDateString,
                    fontSize = 16.sp,
                )
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.padding(top = 12.dp),
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Activity:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Image(
                            painter = painterResource(schedule.scheduleType.icon),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 12.dp),
                        )

                        Text(
                            text = schedule.scheduleType.action,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 6.dp),
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "Health:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        Image(
                            painter = painterResource(R.drawable.ic_heart),
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(start = 12.dp),
                        )

                        Text(
                            text = schedule.scheduleType.healthPlusPercentage,
                            fontSize = 16.sp,
                            color = healthGood,
                            modifier = Modifier.padding(start = 6.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1F))

                Image(
                    painter = painterResource(R.drawable.ic_complete),
                    contentDescription = null,
                    modifier = Modifier.clip(CircleShape).clickable { onAcceptClicked(compositeTask) }
                )
            }
        }
    }
}