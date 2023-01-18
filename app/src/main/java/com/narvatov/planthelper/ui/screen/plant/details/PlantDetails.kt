package com.narvatov.planthelper.ui.screen.plant.details

import androidx.activity.result.launch
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.narvatov.planthelper.models.ui.task.Tab.History
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.narvatov.planthelper.R
import com.narvatov.planthelper.models.ui.task.TaskUIElement
import com.narvatov.planthelper.models.ui.task.tabs
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigateToEditPlant
import com.narvatov.planthelper.ui.screen.plant.common.PlantAgeHealth
import com.narvatov.planthelper.ui.screen.plant.create.ChooseArea
import com.narvatov.planthelper.ui.screen.task.*
import com.narvatov.planthelper.ui.screen.task.ACTIVE_TASKS_INDEX
import com.narvatov.planthelper.ui.screen.task.HISTORY_TASKS_INDEX
import com.narvatov.planthelper.ui.screen.task.tab.TabContent
import com.narvatov.planthelper.ui.screen.task.tab.TaskTab
import com.narvatov.planthelper.ui.screen.task.tab.TaskTabRow
import com.narvatov.planthelper.ui.spaceBetween
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.TaskViewModel
import com.narvatov.planthelper.ui.viewmodel.plant.details.PlantDetailsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlantDetails(plantId: Long) {
    val viewModel = getViewModel<PlantDetailsViewModel>(parameters = { parametersOf(plantId) })
    val taskViewModel = getViewModel<TaskViewModel>(
        parameters = { parametersOf(plantId) }
    )
    with(viewModel) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = LightGreyBackground)
        ) {
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
                            Row(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 30.dp),
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1F)
                                        .fillMaxWidth()
                                        .aspectRatio(0.66F),
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(context = LocalContext.current)
                                            .crossfade(true)
                                            .data(plantDetailsUiState.plant.imageUrl)
                                            .build(),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(Shapes.large),
                                    )
                                }

                                Spacer(modifier = Modifier.width(20.dp))

                                ChooseArea(
                                    text = "Edit\nthe plant",
                                    image = R.drawable.ic_edit_big,
                                    onClick = { navigateToEditPlant(plantDetailsUiState.plant) },
                                    modifier = Modifier.weight(1F)
                                )
                            }

                            PlantAdditionalInfo(
                                plant = plantDetailsUiState.plant,
                                onEditClicked = { plant ->
                                    navigateToEditPlant(plant)
                                },
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .padding(horizontal = 16.dp),
                            )

                            PlantAgeHealth(
                                plant = plantDetailsUiState.plant,
                                ageSize = 18.sp,
                                modifier = Modifier.padding(
                                    start = 16.dp,
                                    end = 40.dp,
                                    top = 20.dp,
                                ),
                            )

                            Spacer(modifier = Modifier.height(40.dp))
                        }
                    }

                    item {
                        TaskTabRow(
                            selectedTabIndex = tabIndex,
                        ) { index, tab ->
                            TaskTab(
                                selected = tabIndex == index,
                                historyTab = index == tabs.indexOf(History),
                                onClick = { tabIndex = index },
                                onHistoryTabOpened = { completePlantFailedTasks(plantId) },
                                text = {
                                    TabContent(
                                        selected = tabIndex == index,
                                        historyTasks = taskViewModel.tasksUiState.historyTasks,
                                        tab = tab,
                                    )
                                }
                            )
                        }
                    }

                    val tasks = when (tabIndex) {
                        ACTIVE_TASKS_INDEX -> taskViewModel.tasksUiState.activeTasks
                        HISTORY_TASKS_INDEX -> taskViewModel.tasksUiState.historyTasks
                        else -> emptyList()
                    }

                    ListSpacer()

                    items(tasks) { item ->
                        Box(
                            modifier = Modifier.padding(horizontal = 16.dp)
                        ) {
                            when (item) {
                                is TaskUIElement.CompositeTask -> {
                                    TaskCard(
                                        compositeTask = item,
                                        onTaskClicked = {},
                                        onAcceptClicked = { taskViewModel.completeTask(it.task) },
                                    )
                                }
                                is TaskUIElement.Header -> {
                                    TaskHeader(header = item)
                                }
                            }
                        }
                    }

                    ListSpacer()
                }
            }
        }
    }
}