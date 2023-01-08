package com.narvatov.planthelper.ui.screen.purchase

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.ui.viewmodel.PurchaseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Purchase(
    viewModel: PurchaseViewModel = getViewModel()
) = with(viewModel) {

    when {
        purchaseUiState.loading -> {
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
            Text("LOADING")
        }
        purchaseUiState.error -> {
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
            Text("ERROR")
        }
        else -> {
            LazyColumn {
                items(purchaseUiState.productDetailsList) { productDetails ->
                    Button(onClick = {

                    }) {
                        Text(productDetails.name, fontSize = 50.sp)
                    }
                }
            }
        }
    }

//    onClick = {
//        val productDetailsParamsList = listOf(
//            BillingFlowParams.ProductDetailsParams.newBuilder()
//                .setProductDetails(productDetails!!)
//                // to get an offer token, call ProductDetails.subscriptionOfferDetails()
//                // for a list of offers that are available to the user
//                .setOfferToken(productDetails!!.subscriptionOfferDetails!!.first().offerToken)
//                .build()
//        )
//
//        val billingFlowParams = BillingFlowParams.newBuilder()
//            .setProductDetailsParamsList(productDetailsParamsList)
//            .build()
//
//// Launch the billing flow
//        val billingResult = billingClient.launchBillingFlow(this, billingFlowParams)
//    }
}