package com.example.planthelper.ui.screen.plant.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planthelper.R
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.utils.previewPlant

@Composable
fun HealthProgress(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ImageText(
            imageRes = R.drawable.ic_heart,
            textRes = R.string.health,
        )

        LinearTextProgressIdicator(
            plant = plant,
            modifier = Modifier.padding(top = 11.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HealhProgressPreview() {
    HealthProgress(
        plant = previewPlant,
    )
}