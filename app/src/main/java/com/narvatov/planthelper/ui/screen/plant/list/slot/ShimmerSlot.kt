package com.narvatov.planthelper.ui.screen.plant.list.slot

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.models.ui.plants.slot.SlotGradient
import com.narvatov.planthelper.ui.Shimmer
import com.narvatov.planthelper.ui.generateWidth
import com.narvatov.planthelper.utils.UnitCallback
import com.valentinilk.shimmer.shimmer

@Composable
fun ShimmerSlot(
    title: String,
    titleColor: Color,
    @DrawableRes drawableId: Int,
    drawableTint: Color,
    backgroundColor: Color,
    gradientColors: List<Color>,
    shimmerColor: Color,
    onSlotClicked: UnitCallback,
    modifier: Modifier = Modifier,
) {
    SlotTemplate(
        backgroundColor = backgroundColor,
        modifier = modifier.clickable { onSlotClicked() }
    ) {
        Box(modifier = Modifier.shimmer()) {
            Shimmer(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .height(16.dp)
                    .fillMaxWidth(),
                shimmerColor = shimmerColor,
            )
        }

        Column(
            modifier = Modifier
                .padding(top = 64.dp)
                .padding(horizontal = 16.dp)
                .align(Alignment.TopCenter)
        ) {
            Image(
                painter = painterResource(drawableId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = drawableTint),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(color = Color.White)
                    .align(Alignment.CenterHorizontally),
            )

            Text(
                text = title,
                modifier = Modifier.padding(top = 12.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = titleColor,
            )
        }

        SlotGradient(gradientColors = gradientColors)

        Column(
            modifier = Modifier
                .padding(bottom = 12.dp)
                .padding(horizontal = 8.dp)
                .align(Alignment.BottomStart)
                .shimmer()
        ) {
            val bigTextWidth by remember { mutableStateOf(generateWidth(3..9)) }

            Shimmer(
                modifier = Modifier
                    .height(16.dp)
                    .fillMaxWidth(bigTextWidth),
                shimmerColor = shimmerColor,
            )

            val normalTextWidth by remember { mutableStateOf(generateWidth(5..10)) }

            Shimmer(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .height(13.dp)
                    .fillMaxWidth(normalTextWidth),
                shimmerColor = shimmerColor,
            )
        }
    }
}