package com.narvatov.planthelper.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.LargePrimaryButton
import com.narvatov.planthelper.ui.theme.RegularBlack

@Composable
fun SettingsNotifications() {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Your notifications will appear from Saturday to Sunday (8 AM - 7 PM)",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = RegularBlack,
            modifier = Modifier.padding(top = 20.dp)
        )

        Text(
            text = stringResource(R.string.customize_days),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RegularBlack,
            modifier = Modifier.padding(top = 20.dp)
        )

        NotificationOption(
            text = stringResource(R.string.weekend),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )

        NotificationOption(
            text = stringResource(R.string.weekday),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )



        Text(
            text = stringResource(R.string.customize_time),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RegularBlack,
            modifier = Modifier.padding(top = 20.dp)
        )

        NotificationOption(
            text = stringResource(R.string.ftr),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )

        NotificationOption(
            text = stringResource(R.string.str),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )

        NotificationOption(
            text = stringResource(R.string.ttr),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )

        NotificationOption(
            text = stringResource(R.string.fotr),
            enabled = true,
            onCheckChanged = {
                //TODO
            }
        )

        LargePrimaryButton(
            text = stringResource(R.string.save),
            modifier = Modifier.padding(top = 20.dp),
            onClick = {
                //TODO
            }
        )
    }
}