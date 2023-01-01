package com.narvatov.planthelper.ui.screen.plant.common

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.getColor
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.theme.healthColorMap
import com.narvatov.planthelper.ui.theme.healthTextColorMap
import com.narvatov.planthelper.utils.previewPlant

@Composable
fun LinearTextProgressIdicator(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        LinearProgressIndicator(
            progress = plant.health.toFloat(),
            color = healthColorMap.getColor(plant.health),
            backgroundColor = Color.White,
            modifier = Modifier.width(125.dp)
                .height(16.dp)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = RegularGrey,
                    shape = RoundedCornerShape(20.dp)
                )
        )

        Text(
            text = plant.healthPercents,
            modifier = Modifier.align(Alignment.Center),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = healthTextColorMap.getColor(plant.health)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LinearTextProgressIdicatorPreview() {
    LinearTextProgressIdicator(
        plant = previewPlant,
    )
}