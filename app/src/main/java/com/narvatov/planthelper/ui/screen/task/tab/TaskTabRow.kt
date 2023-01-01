package com.narvatov.planthelper.ui.screen.task.tab

import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.models.ui.task.Tab
import com.narvatov.planthelper.models.ui.task.tabs
import com.narvatov.planthelper.ui.theme.PrimaryColor

@Composable
fun TaskTabRow(
    selectedTabIndex: Int,
    indicator: @Composable @UiComposable
        (tabPositions: List<TabPosition>) -> Unit = @Composable { tabPositions ->
        TabRowDefaults.Indicator(
            Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
        )
    },
    tabsComposable: @Composable @UiComposable (Int, Tab) -> Unit,
) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = PrimaryColor,
        divider = {
            TabRowDefaults.Divider(
                thickness = 2.dp,
                color = Color.White
            )
        },
        indicator = indicator,
    ) {
        tabs.forEachIndexed { index, tab ->
            tabsComposable(index, tab)
        }
    }
}