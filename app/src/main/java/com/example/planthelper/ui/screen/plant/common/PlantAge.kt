package com.example.planthelper.ui.screen.plant.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planthelper.R
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.utils.previewPlant

@Composable
fun PlantAge(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ImageText(
            imageRes = R.drawable.ic_plant,
            textRes = R.string.age,
        )

        Text(
            text = plant.ageString,
            modifier = Modifier.padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlantAgePreview() {
    PlantAge(
        plant = previewPlant,
    )
}