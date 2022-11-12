package com.example.planthelper.ui.screen.plant.list.slot

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.planthelper.models.ui.plants.PlantSlot
import com.example.planthelper.ui.screen.plant.common.PlantAgeHealth
import com.example.planthelper.utils.GenericCallback
import com.example.planthelper.utils.previewPlant


@Composable
fun FilledPlantSlot(
    slot: PlantSlot.FilledSlot,
    onPlantClicked: GenericCallback<PlantSlot.FilledSlot>,
    modifier: Modifier = Modifier,
) = with(slot.plant) {
    SlotTemplate(
        title = plantName,
        modifier = modifier.clickable { onPlantClicked(slot) }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp).clip(CircleShape),
        )

        PlantAgeHealth(
            plant = slot.plant,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilledPlantSlotPreview() {
    FilledPlantSlot(
        slot = PlantSlot.FilledSlot(previewPlant),
        {},
        modifier = Modifier.padding(20.dp)
    )
}
