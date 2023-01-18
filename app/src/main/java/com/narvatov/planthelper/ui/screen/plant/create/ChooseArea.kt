package com.narvatov.planthelper.ui.screen.plant.create

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.utils.UnitCallback

@Composable
fun ChooseArea(
    text: String,
    @DrawableRes image: Int,
    onClick: UnitCallback,
    modifier: Modifier = Modifier,
) {
    val stroke = Stroke(width = 10F,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f), 0f)
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(0.66F),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = PrimaryColor,
                style = stroke,
                cornerRadius = CornerRadius(20.dp.toPx())
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.large)
                .background(Color(0xFFD0FFD0))
                .clickable { onClick() },
        ) {

            Spacer(modifier = Modifier.weight(0.19F))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.56F)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                WeightedSpacer()

                Text(
                    text = text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    color = RegularBlack,
                    lineHeight = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.weight(0.25F))

        }
    }
}