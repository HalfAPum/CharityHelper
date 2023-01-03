package com.narvatov.planthelper.ui.screen.plant.create.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun SearchCard(
    plant: Plant,
    onPlantClicked: (Plant) -> Unit,
    modifier: Modifier = Modifier
) = with(plant) {
    Card(
        modifier = Modifier
            .height(110.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = Shapes.large)
            .clip(Shapes.large)
            .then(modifier.clickable { onPlantClicked(plant) }),
        backgroundColor = Color.White,
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(Shapes.medium),
            )

            Text(
                text = originName,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}