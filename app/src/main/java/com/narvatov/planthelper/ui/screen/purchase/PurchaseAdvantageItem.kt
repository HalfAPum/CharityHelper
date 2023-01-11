package com.narvatov.planthelper.ui.screen.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R

@Composable
fun PurchaseAdvantageItem(text: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(start = 24.dp)) {
        Image(
            painter = painterResource(R.drawable.green_checkbox),
            contentDescription = null,
        )

        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}