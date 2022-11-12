package com.example.planthelper.ui.screen.plant.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.ui.plants.PlantSlot.*
import com.example.planthelper.ui.ListSpacer
import com.example.planthelper.ui.screen.plant.list.slot.EmptySlot
import com.example.planthelper.ui.screen.plant.list.slot.FilledPlantSlot
import com.example.planthelper.ui.screen.plant.list.slot.LockedSlot
import com.example.planthelper.ui.theme.LightGreyBackground
import com.example.planthelper.ui.viewmodel.PlantViewModel
import com.example.planthelper.utils.GenericCallback
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantsScreen(
    onPlantClicked: GenericCallback<Plant>,
    onEmptySlotClicked: UnitCallback,
    onLockedSlotClicked: UnitCallback,
    viewModel: PlantViewModel = getViewModel(),
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreyBackground)
            .padding(horizontal = 16.dp)
    ) {
        ListSpacer()

        items(viewModel.plantSlotsUiState.plantSlots) { slot ->
            when (slot) {
                is FilledSlot -> FilledPlantSlot(
                    slot = slot,
                    onPlantClicked = { onPlantClicked(it.plant) },
                )
                EmptySlot -> EmptySlot(
                    onEmptySlotClicked = { onEmptySlotClicked() },
                )
                LockedSlot -> LockedSlot(
                    onLockedSlotClicked = { onLockedSlotClicked() },
                )
            }
        }

        ListSpacer()
    }
}