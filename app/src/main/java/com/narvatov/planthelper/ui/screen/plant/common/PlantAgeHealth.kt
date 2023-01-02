package com.narvatov.planthelper.ui.screen.plant.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.utils.previewPlant

@Composable
fun PlantAgeHealth(
    plant: Plant,
    ageSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        PlantAge(
            plant = plant,
            ageSize = ageSize,
            modifier = Modifier.weight(1F),
        )

        HealthProgress(
            plant = plant,
            modifier = Modifier.padding(start = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantAgeHealthPreview() {
    PlantAgeHealth(
        plant = previewPlant,
    )
}