package com.example.planthelper.ui.screen.plant.list.slot

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planthelper.R
import com.example.planthelper.utils.UnitCallback

@Composable
fun EmptySlot(
    onEmptySlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    ShimmerSlot(
        drawableId = R.drawable.ic_add,
        backgroundColor = Color.White,
        shimmerColor = Color.Gray,
        onSlotClicked = onEmptySlotClicked,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun EmptySlotPreview() {
    EmptySlot(
        {},
        modifier = Modifier.padding(20.dp),
    )
}