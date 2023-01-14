package com.narvatov.planthelper.models.ui.plants.slot

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.Purchase
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.screen.plant.list.slot.ShimmerSlot
import com.narvatov.planthelper.ui.theme.SecondaryColor
import com.narvatov.planthelper.ui.theme.plantLightGreyVerticalGradientColors
import com.narvatov.planthelper.ui.theme.plantWhiteVerticalGradientColors

object LockedPlantSlot : PlantSlot {

    @Composable
    override fun renderSlotUi() {
        ShimmerSlot(
            title = stringResource(R.string.unlock_space),
            titleColor = Color.White,
            drawableId = R.drawable.ic_lock,
            drawableTint = SecondaryColor,
            backgroundColor = Color(0xFF8E8E8E),
            gradientColors = plantLightGreyVerticalGradientColors,
            shimmerColor = Color.White,
            onSlotClicked = ::navigateToPurchase,
        )
    }

    private fun navigateToPurchase() {
        navigate(Purchase)
    }
}