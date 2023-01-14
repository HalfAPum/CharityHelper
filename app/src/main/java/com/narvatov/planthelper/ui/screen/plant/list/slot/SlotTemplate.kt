package com.narvatov.planthelper.ui.screen.plant.list.slot

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun SlotTemplate(
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        modifier = Modifier
            .height(220.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = Shapes.large)
            .clip(Shapes.large)
            .then(modifier),
        backgroundColor = backgroundColor,
    ) {
        Box(content = content)
    }
}