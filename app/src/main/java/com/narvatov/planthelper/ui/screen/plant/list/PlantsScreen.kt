package com.narvatov.planthelper.ui.screen.plant.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.viewmodel.plant.list.PlantViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantsScreen(viewModel: PlantViewModel = getViewModel()) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreyBackground)
            .padding(horizontal = 16.dp)
    ) {
        ListSpacer()

        items(viewModel.plantSlotsUiState.plantSlots) { slot ->
            slot.renderSlotUi()
        }

        ListSpacer()
    }
}