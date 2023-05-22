package com.narvatov.planthelper.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun Scaffold(
    navController: NavHostController,
    bottomBar: @Composable (NavHostController) -> Unit = {},
    content: @Composable (NavHostController, PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { bottomBar(navController) },
        content = { paddingValues ->  content(navController, paddingValues) },
    )
}

/**
 * [Spacer] for lazy lists.
 *
 * By default applies [Arrangement] from your [LazyListScope].
 */
fun LazyListScope.ListSpacer(modifier: Modifier = Modifier) {
    item { Spacer(modifier = modifier) }
}

fun LazyGridScope.GridSpacer(columns: Int, modifier: Modifier = Modifier) {
    items(columns) { Spacer(modifier = modifier)}
}

@Composable
fun Shimmer(
    shimmerColor: Color = Color.Gray,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = modifier
        .clip(Shapes.small)
        .background(shimmerColor)
    )
}

@Composable
fun RowScope.WeightedSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(1F))
}

@Composable
fun ColumnScope.WeightedSpacer(modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.weight(1F))
}