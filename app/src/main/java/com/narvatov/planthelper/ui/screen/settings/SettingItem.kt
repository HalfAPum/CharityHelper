package com.narvatov.planthelper.ui.screen.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.WeightedSpacer
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.utils.UnitCallback

@Composable
fun SettingItem(
    @DrawableRes
    image: Int,
    @StringRes
    text: Int,
    onClick: UnitCallback,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .clip(Shapes.large)
            .background(color = Color(0xFFCEFFCA))
            .clickable { onClick() }
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Image(
            painter = painterResource(image),
            contentDescription = null,
        )

        Text(
            text = stringResource(text),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = RegularBlack,
            modifier = Modifier.padding(start = 8.dp)
        )

        WeightedSpacer()

        Image(
            painter = painterResource(R.drawable.ic_back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(PrimaryColor),
            modifier = Modifier.rotate(180F)
        )

        Spacer(modifier = Modifier.width(20.dp))
    }

}