package com.narvatov.planthelper.ui.screen.plant.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.narvatov.planthelper.R

@Composable
fun ImageText(
    @DrawableRes imageRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
                .align(Alignment.CenterVertically)
        )

        Text(
            text = stringResource(textRes),
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageTextPreview() {
    ImageText(
        imageRes = R.drawable.ic_heart,
        textRes = R.string.age,
    )
}