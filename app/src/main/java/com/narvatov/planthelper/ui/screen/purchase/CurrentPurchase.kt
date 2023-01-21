package com.narvatov.planthelper.ui.screen.purchase

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.ui.navigation.Purchase
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.theme.PrimaryColor
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.PurchaseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun CurrentPurchase(
    viewModel: PurchaseViewModel = getViewModel()
) = with (viewModel) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 20.dp),
    ) {
        Text(
            text = stringResource(R.string.current_subscription),
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )


        purchaseUiState.subscriptionDetailsList.firstOrNull {
            it.productDetails?.productId == purchaseUiState.purchasedList.firstOrNull()?.productId
        }?.let { subscriptionDetails ->
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 10.dp, shape = Shapes.large)
                    .clip(Shapes.large)
                    .clickable {}
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    Text(
                        text = subscriptionDetails.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                    )

                    Text(
                        text = "${
                            subscriptionDetails.productDetails?.subscriptionOfferDetails
                                ?.first()?.pricingPhases?.pricingPhaseList?.first()?.formattedPrice
                        } per month",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp)
                    )

                    PurchaseAdvantageItem(
                        boldStartText = subscriptionDetails.newSlotsBoldText,
                        normalText = subscriptionDetails.newSlotsText,
                        modifier = Modifier.padding(top = 16.dp)
                    )

                    if (subscriptionDetails.noAds != null) {
                        PurchaseAdvantageItem(normalText = subscriptionDetails.noAds)
                    }

                    PurchaseAdvantageItem(
                        boldStartText = subscriptionDetails.dailyTasksBoldText,
                        normalText = subscriptionDetails.dailyTasksText
                    )
                }
            }
        }

        Card(
            backgroundColor = PrimaryColor,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .height(40.dp)
                .clip(shape = Shapes.large)
                .clickable { navigate(Purchase) }
        ) {
            Box {
                Text(
                    text = stringResource(R.string.change_sucscription),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}