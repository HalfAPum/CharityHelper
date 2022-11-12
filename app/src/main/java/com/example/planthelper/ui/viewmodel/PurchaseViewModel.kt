package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.purchase.EmptyPurchaseUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PurchaseViewModel : ViewModel() {

    var purchaseUiState by mutableStateOf(EmptyPurchaseUiState())
        private set
}