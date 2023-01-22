package com.narvatov.planthelper.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
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
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.CurrentPurchase
import com.narvatov.planthelper.ui.navigation.Purchase
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.screen.plant.create.OutlinedPlantTextField
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.SecondaryColor
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.PurchaseViewModel
import com.narvatov.planthelper.ui.viewmodel.SettingsViewModel
import com.narvatov.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = getViewModel(),
    purchaseViewModel: PurchaseViewModel = getViewModel()
) = with(viewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(top = 20.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        SettingItem(
            image = R.drawable.ic_notifications,
            text = R.string.notifications,
            onClick = { popBack() }
        )

        SettingItem(
            image = R.drawable.ic_subscription,
            text = R.string.subscription_plan,
            onClick = {
                val goToCurrentPurchases = purchaseViewModel.purchaseUiState.subscriptionDetailsList.any {
                    it.productDetails?.productId == purchaseViewModel.purchaseUiState.purchasedList.firstOrNull()?.productId
                }

                val destination = if (goToCurrentPurchases) CurrentPurchase else Purchase

                navigate(destination)
            }
        )

        SettingItem(
            image = R.drawable.ic_issue,
            text = R.string.repost_an_issue,
            onClick = { popBack() }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreyBackground)
            .padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        OutlinedPlantTextField(
            label = stringResource(R.string.busy_hours_text),
            enabled = false
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

    }
}

@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    SettingsScreen()
}