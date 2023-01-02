package com.narvatov.planthelper.models.ui.plants.slot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.CreatePlant
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.navigationEditPlantId
import com.narvatov.planthelper.ui.screen.plant.list.slot.ShimmerSlot

object EmptyPlantSlot : PlantSlot {

    @Composable
    override fun renderSlotUi() {
        ShimmerSlot(
            drawableId = R.drawable.ic_add,
            backgroundColor = Color.White,
            shimmerColor = Color.Gray,
            onSlotClicked = ::navigateToCreatePlant,
        )
    }

    private fun navigateToCreatePlant() {
        navigationEditPlantId = null
        navigate(CreatePlant)
    }

}