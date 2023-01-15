package com.narvatov.planthelper.models.ui.purchase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.Purchase
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.Shapes

@Composable
fun PurchaseDialog() {
    Box {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 16.dp)
                .padding(end = 8.dp)
                .background(color = Color.White, shape = Shapes.large)
        ) {
            Text(
                text = stringResource(R.string.upgrade_premium),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 32.dp)
            )

            Text(
                text = stringResource(R.string.add_more_with),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 20.dp),
            )

            Button(
                onClick = { UiNavigationEventPropagator.navigate(Purchase) },
                shape = Shapes.large,
                modifier = Modifier.padding(vertical = 32.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = PrimaryColor)
            ) {
                Text(
                    text = stringResource(R.string.get_premium),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }

        Image(
            painter = painterResource(R.drawable.ic_exit),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(CircleShape)
                .background(Color.White)
                .clickable { popBack() }
        )
    }
}