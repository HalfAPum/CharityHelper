package com.narvatov.planthelper.ui.screen.purchase

import android.app.Activity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.narvatov.planthelper.data.utils.billingFlowParams
import com.narvatov.planthelper.ui.viewmodel.PurchaseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Purchase(
    viewModel: PurchaseViewModel = getViewModel()
) = with(viewModel) {
    val activity = LocalContext.current as Activity

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
                        val billingFlowParams = productDetails.billingFlowParams

                        launchBillingFlow(activity, billingFlowParams)
                    }) {
                        Text(productDetails.name, fontSize = 50.sp)
                    }
                }
            }
        }
    }
}