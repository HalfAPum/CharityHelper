package com.example.planthelper.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.planthelper.R
import com.example.planthelper.ui.screen.plant.create.OutlinedPlantTextField
import com.example.planthelper.ui.theme.LightGreyBackground
import com.example.planthelper.ui.viewmodel.SettingsViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    onPurchaseClicked: UnitCallback,
    viewModel: SettingsViewModel = getViewModel()
) = with(viewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreyBackground)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        OutlinedPlantTextField(
            label = stringResource(R.string.busy_hours_text),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Text(
                    text = stringResource(R.string.from),
                    fontSize = 16.sp,
                )

                TimePickerButton(
                    text = settingsUiState.busyTimeStart,
                    onTimeChanged = { updateBusyTimeStart(it) },
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                Text(
                    text = stringResource(R.string.to),
                    fontSize = 16.sp,
                )

                TimePickerButton(
                    text = settingsUiState.busyTimeEnd,
                    onTimeChanged = { updateBusyTimeEnd(it) },
                    modifier = Modifier.padding(horizontal = 4.dp),
                )

                Text(
                    text = stringResource(R.string.weekdays),
                    fontSize = 16.sp,
                )
            }
        }

        OutlinedPlantTextField(
            text = settingsUiState.currentPlan,
            label = stringResource(R.string.current_pay_plan),
            enabled = false,
            onClick = { onPurchaseClicked() },
            modifier = Modifier.padding(top = 20.dp),
        )

        Button(
            onClick = { onPurchaseClicked() },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Text(
                text = stringResource(R.string.get_premium),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.fillMaxHeight())

//        Button(
//            onClick = { onPurchaseClicked() },
//            shape = RoundedCornerShape(20.dp),
//            modifier = Modifier.padding(vertical = 24.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray)
//        ) {
//            Text(
//                text = "Report an issue",
//                fontSize = 16.sp,
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingsScreen({})
}