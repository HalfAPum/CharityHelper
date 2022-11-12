package com.example.planthelper.ui.screen.purchase

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.planthelper.ui.viewmodel.PurchaseViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun Purchase(
    viewModel: PurchaseViewModel = getViewModel()
) {
    Text("PURCHASE SCREEN")
}