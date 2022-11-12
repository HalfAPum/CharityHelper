package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.purchase.EmptyPurchaseUiState

class PurchaseViewModel : ViewModel() {

    var purchaseUiState by mutableStateOf(EmptyPurchaseUiState())
        private set
}