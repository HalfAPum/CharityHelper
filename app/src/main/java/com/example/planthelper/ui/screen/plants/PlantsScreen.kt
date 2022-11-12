package com.example.planthelper.ui.screen.plants

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.planthelper.models.ui.plants.PlantSlot.FilledSlot
import com.example.planthelper.models.ui.plants.PlantSlot.EmptySlot
import com.example.planthelper.models.ui.plants.PlantSlot.LockedSlot
import com.example.planthelper.ui.viewmodel.PlantViewModel
import com.example.planthelper.utils.GenericCallback
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantsScreen(
    onPlantClicked: GenericCallback<FilledSlot>,
    onEmptySlotClicked: UnitCallback,
    onLockedSlotClicked: UnitCallback,
    viewModel: PlantViewModel = getViewModel(),
) {
    LazyVerticalGrid(columns = viewModel.columns) {
        items(viewModel.plantSlotsUiState.plantSlots) { slot ->
            when (slot) {
                is FilledSlot -> FilledPlantSlot(
                    slot = slot,
                    onPlantClicked = { onPlantClicked(it) },
                    onRemovePlantClicked = { viewModel.removePlant(it) }
                )
                EmptySlot -> EmptySlot(
                    onEmptySlotClicked = { onEmptySlotClicked() }
                )
                LockedSlot -> LockedSlot(
                    onLockedSlotClicked = { onLockedSlotClicked() }
                )
            }
        }
    }
}

@Composable
fun FilledPlantSlot(
    slot: FilledSlot,
    onPlantClicked: GenericCallback<FilledSlot>,
    onRemovePlantClicked: GenericCallback<FilledSlot>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable { onPlantClicked(slot) }) {
        Text(slot.toString())
    }
}

@Composable
fun EmptySlot(
    onEmptySlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable { onEmptySlotClicked() }) {
        Text("EMPTY")
    }
}

@Composable
fun LockedSlot(
    onLockedSlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable { onLockedSlotClicked() }) {
        Text("LOCKED")
    }
}