package com.narvatov.planthelper.ui.screen.plant.list.slot

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.utils.UnitCallback

@Composable
fun LockedSlot(
    onLockedSlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    ShimmerSlot(
        drawableId = R.drawable.ic_lock,
        backgroundColor = Color(0xFF8E8E8E),
        shimmerColor = Color.White,
        onSlotClicked = onLockedSlotClicked,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun LockedSlotPreview() {
    LockedSlot(
        {},
        modifier = Modifier.padding(20.dp)
    )
}