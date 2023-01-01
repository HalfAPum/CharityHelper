package com.narvatov.planthelper.ui.screen.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.narvatov.planthelper.models.ui.task.tabs
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigateToPlantDetails
import com.narvatov.planthelper.ui.screen.task.tab.TabContent
import com.narvatov.planthelper.ui.screen.task.tab.TaskTabRow
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.viewmodel.TaskViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TasksScreen(
    viewModel: TaskViewModel = getViewModel(),
    modifier: Modifier = Modifier
) = with(viewModel) {
    val scope = rememberCoroutineScope()

    var tabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()

    Column(modifier = modifier) {
        TaskTabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            },
        ) { index, tab ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    tabIndex = index

                    scope.launch {
                        pagerState.animateScrollToPage(tabIndex)
                    }
                },
                text = {
                    TabContent(
                        selected = pagerState.currentPage == index,
                        historyTasks = tasksUiState.historyTasks,
                        tab = tab,
                    )
                }
            )
        }

        HorizontalPager(
            count = tabs.size,
            state = pagerState,
        ) { tabIndex ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(LightGreyBackground)
                    .padding(horizontal = 16.dp)
            ) {
                ListSpacer()

                val tasks = when(tabIndex) {
                    ACTIVE_TASKS_INDEX -> tasksUiState.activeTasks
                    HISTORY_TASKS_INDEX -> tasksUiState.historyTasks
                    else -> emptyList()
                }

                items(tasks) { item ->
                    TaskCard(
                        compositeTask = item,
                        onTaskClicked = { navigateToPlantDetails(it.plant) },
                        onAcceptClicked = { completeTask(it.task) },
                    )
                }

                ListSpacer()
            }
        }
    }
}

internal const val ACTIVE_TASKS_INDEX = 0
internal const val HISTORY_TASKS_INDEX = 1


@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    TasksScreen()
}