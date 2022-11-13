package com.example.planthelper.ui.screen.plant.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.ui.ListSpacer
import com.example.planthelper.ui.screen.plant.common.PlantAgeHealth
import com.example.planthelper.ui.screen.task.ACTIVE_TASKS_INDEX
import com.example.planthelper.ui.screen.task.HISTORY_TASKS_INDEX
import com.example.planthelper.ui.screen.task.TaskCard
import com.example.planthelper.ui.screen.task.tabs
import com.example.planthelper.ui.spaceBetween
import com.example.planthelper.ui.theme.LightGreyBackground
import com.example.planthelper.ui.viewmodel.PlantDetailsViewModel
import com.example.planthelper.ui.viewmodel.TaskViewModel
import com.example.planthelper.utils.GenericCallback
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantDetails(
    plantId: Long,
    onDeleteClicked: UnitCallback,
    onEditClicked: GenericCallback<Plant>,
) {
    val viewModel = getViewModel<PlantDetailsViewModel>(parameters = { parametersOf(plantId) })
    val taskViewModel = getViewModel<TaskViewModel>(parameters = { parametersOf(plantId) })
    with(viewModel) {
        var tabIndex by remember { mutableStateOf(0) }

        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ) {
            LazyColumn(
                modifier = Modifier.background(color = LightGreyBackground),
                verticalArrangement = Arrangement.spaceBetween(20.dp)
            ) {
                item {
                    Column(modifier = Modifier.background(color = Color.White)) {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .crossfade(true)
                                .data(plantDetailsUiState.plant.imageUrl)
                                .build(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth(),
                        )

                        PlantAdditionalInfo(
                            plant = plantDetailsUiState.plant,
                            onEditClicked = { onEditClicked(it) },
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .padding(horizontal = 16.dp),
                        )

                        PlantAgeHealth(
                            plant = plantDetailsUiState.plant,
                            modifier = Modifier.padding(
                                start = 16.dp,
                                end = 40.dp,
                                top = 20.dp,
                            ),
                        )

                        Spacer(modifier = Modifier.height(40.dp))

                        Spacer(
                            modifier = Modifier
                                .height(32.dp)
                                .background(color = LightGreyBackground)
                        )

                        TabRow(selectedTabIndex = tabIndex) {
                            tabs.forEachIndexed { index, title ->
                                Tab(
                                    selected = tabIndex == index,
                                    onClick = { tabIndex = index },
                                    text = { Text(text = title) }
                                )
                            }
                        }
                    }
                }

                val tasks = when (tabIndex) {
                    ACTIVE_TASKS_INDEX -> taskViewModel.tasksUiState.activeTasks
                    HISTORY_TASKS_INDEX -> taskViewModel.tasksUiState.historyTasks
                    else -> emptyList()
                }

                items(tasks) { item ->
                    Box(
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        TaskCard(
                            compositeTask = item,
                            onTaskClicked = {},
                            onAcceptClicked = { taskViewModel.completeTask(it.task) },
                        )
                    }
                }

                ListSpacer()
            }
        }
    }
}