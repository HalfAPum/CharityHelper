package com.narvatov.planthelper.ui.screen.purchase

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.utils.billingFlowParams
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.theme.Shapes
import com.narvatov.planthelper.ui.viewmodel.PurchaseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun PurchaseScreen(
    viewModel: PurchaseViewModel = getViewModel()
) = with(viewModel) {
    val activity = LocalContext.current as Activity

    when {
        purchaseUiState.loading -> {
            Box(modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)) {
                Text(
                    text = "Loading purchases...",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        purchaseUiState.error -> {
            Box(modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp)) {
                Text(
                    text = purchaseUiState.errorMessage,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else -> {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 20.dp),
            ) {
                item {
                    Text(
                        text = stringResource(R.string.choose_plan),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                }

                items(purchaseUiState.subscriptionDetailsList.filter {
                    it.productDetails?.productId != purchaseUiState.purchasedList.firstOrNull()?.productId
                }) { subscriptionDetails ->
                    Card(
                        backgroundColor = subscriptionDetails.backgroundColor,
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .shadow(elevation = 10.dp, shape = Shapes.large)
                            .clip(Shapes.large)
                            .clickable {
//                                enabled = !purchaseUiState.purchasedList.any { it.productId == productDetails.productId }

                                val billingFlowParams = subscriptionDetails.productDetails?.billingFlowParams(
                                    oldPurchaseToken = purchaseUiState.purchasedList.firstOrNull()?.purchaseToken,
                                )

                                launchBillingFlow(activity, billingFlowParams)
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 20.dp)
                        ) {
                            if (subscriptionDetails.headerText != null) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 4.dp, top = 4.dp)
                                        .background(
                                            color = Color(0xFF00AC00),
                                            shape = RoundedCornerShape(
                                                topStart = 20.dp,
                                                bottomEnd = 20.dp
                                            )
                                        )
                                ) {
                                    Text(
                                        text = subscriptionDetails.headerText,
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 28.dp, vertical = 8.dp)
                                    )
                                }
                            }

                            Text(
                                text = subscriptionDetails.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier.padding(top = 20.dp, start = 20.dp)
                            )

                            Text(
                                text = "${subscriptionDetails.productDetails?.subscriptionOfferDetails
                                    ?.first()?.pricingPhases?.pricingPhaseList?.first()?.formattedPrice} per month",
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
                                boldStartText = subscriptionDetails.
                                dailyTasksBoldText,
                                normalText = subscriptionDetails.dailyTasksText
                            )
                        }
                    }
                }

                ListSpacer()

                ListSpacer()

                ListSpacer()
            }
        }
    }
}