package com.narvatov.planthelper.models.ui.plants.slot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.CreatePlant
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.navigationEditPlantId
import com.narvatov.planthelper.ui.screen.plant.list.slot.ShimmerSlot
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.theme.plantBlackVerticalGradientColors
import com.narvatov.planthelper.ui.theme.plantWhiteVerticalGradientColors

object EmptyPlantSlot : PlantSlot {

    @Composable
    override fun renderSlotUi() {
        ShimmerSlot(
            title = stringResource(R.string.add_new_plant),
            titleColor = RegularGrey,
            drawableId = R.drawable.ic_add,
            drawableTint = PrimaryColor,
            backgroundColor = Color.White,
            gradientColors = plantBlackVerticalGradientColors,
            shimmerColor = Color.Gray,
            onSlotClicked = ::navigateToCreatePlant,
        )
    }

    private fun navigateToCreatePlant() {
        navigationEditPlantId = null
        navigate(CreatePlant)
    }

}