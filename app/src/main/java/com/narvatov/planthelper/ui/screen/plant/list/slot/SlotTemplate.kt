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

@Composable
fun SlotTemplate(
    title: String,
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    rowContent: @Composable RowScope.() -> Unit,
) {
    Card(
        modifier = Modifier
            .height(110.dp).fillMaxWidth()
            .shadow(elevation = 10.dp, shape = RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .then(modifier),
        backgroundColor = backgroundColor,
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = title,
                modifier = Modifier.padding(top = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )

            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                content = rowContent,
            )
        }
    }
}