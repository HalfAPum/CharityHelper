package com.narvatov.planthelper.ui.screen.plant.list.slot

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.Shimmer
import com.narvatov.planthelper.utils.UnitCallback
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerSlot(
    @DrawableRes drawableId: Int,
    backgroundColor: Color,
    shimmerColor: Color,
    onSlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    SlotTemplate(
        title = stringResource(R.string.add_new_plant),
        backgroundColor = backgroundColor,
        modifier = modifier.clickable { onSlotClicked() }
    ) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(48.dp).clip(CircleShape),
        )

        Row(modifier = Modifier.padding(start = 8.dp)) {
            Column(modifier = Modifier.weight(1F).shimmer()) {
                Shimmer(
                    modifier = Modifier
                        .height(20.dp)
                        .width(47.dp),
                    shimmerColor = shimmerColor,
                )

                Shimmer(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .height(20.dp)
                        .width(84.dp),
                    shimmerColor = shimmerColor,
                )
            }

            Column(modifier = Modifier.padding(start = 16.dp).shimmer()) {
                Shimmer(
                    modifier = Modifier
                        .height(20.dp)
                        .width(70.dp),
                    shimmerColor = shimmerColor,
                )

                Shimmer(
                    modifier = Modifier
                        .padding(top = 11.dp)
                        .height(16.dp)
                        .width(125.dp),
                    shimmerColor = shimmerColor,
                )
            }
        }
    }
}