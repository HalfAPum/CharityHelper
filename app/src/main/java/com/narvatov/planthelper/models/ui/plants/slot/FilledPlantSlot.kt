package com.narvatov.planthelper.models.ui.plants.slot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.navigation.PlantDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigator
import com.narvatov.planthelper.ui.navigation.navigationPlantId
import com.narvatov.planthelper.ui.navigation.withParam
import com.narvatov.planthelper.ui.screen.plant.common.PlantAgeHealth
import com.narvatov.planthelper.ui.screen.plant.list.slot.SlotTemplate
import com.narvatov.planthelper.utils.UnitCallback

class FilledPlantSlot(private val plant: Plant): PlantSlot {

    @Composable
    override fun renderSlotUi() = with(plant) {
        SlotTemplate(
            title = plantName,
            modifier = Modifier.clickable { navigateToPlantDetails() }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(48.dp).clip(CircleShape),
            )

            PlantAgeHealth(
                plant = plant,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

    private fun navigateToPlantDetails() {
        navigationPlantId = plant.id
        UiNavigator.navigationEvents.navigate(PlantDetails
            .withParam(PlantDetails.PLANT_ID_NAV_PARAM, plant.id))
    }

}