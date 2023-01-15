package com.narvatov.planthelper.ui.screen.plant.list.slot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun SlotTemplate(
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(0.66F)
            .shadow(elevation = 10.dp, shape = Shapes.large)
            .clip(Shapes.large)
            .then(modifier),
        backgroundColor = backgroundColor,
    ) {
        Box(content = content)
    }
}