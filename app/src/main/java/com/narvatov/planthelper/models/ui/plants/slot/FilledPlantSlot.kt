package com.narvatov.planthelper.models.ui.plants.slot

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigateToPlantDetails
import com.narvatov.planthelper.ui.screen.plant.common.LinearTextProgressIdicator
import com.narvatov.planthelper.ui.screen.plant.common.PlantAgeHealth
import com.narvatov.planthelper.ui.screen.plant.list.slot.SlotTemplate
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.theme.plantWhiteVerticalGradientColors

class FilledPlantSlot(private val plant: Plant): PlantSlot {

    @Composable
    override fun renderSlotUi() = with(plant) {
        SlotTemplate(
            modifier = Modifier.clickable { navigateToPlantDetails(plant) }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
            )


            LinearTextProgressIdicator(
                plant = plant,
                widthModifier = Modifier.fillMaxWidth(),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(horizontal = 16.dp),
            )


            SlotGradient(gradientColors = plantWhiteVerticalGradientColors)

            Column(
                modifier = Modifier
                    .padding(start = 8.dp, end = 12.dp)
                    .padding(bottom = 12.dp)
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = plant.plantName,
                    color = RegularBlack,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = plant.originName,
                    color = RegularGrey,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}