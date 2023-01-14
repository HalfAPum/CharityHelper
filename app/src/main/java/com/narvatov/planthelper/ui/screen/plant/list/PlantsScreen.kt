package com.narvatov.planthelper.ui.screen.plant.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.ui.GridSpacer
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.viewmodel.plant.list.PlantViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantsScreen(viewModel: PlantViewModel = getViewModel()) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(PLANT_GRID_COUNT),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        GridSpacer(PLANT_GRID_COUNT)

        items(viewModel.plantSlotsUiState.plantSlots) { slot ->
            slot.renderSlotUi()
        }

        GridSpacer(PLANT_GRID_COUNT)
    }
}

private const val PLANT_GRID_COUNT = 2