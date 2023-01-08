package com.narvatov.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.data.repository.BillingRepository
import com.narvatov.planthelper.models.ui.purchase.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PurchaseViewModel(private val billingRepository: BillingRepository) : ViewModel() {

    var purchaseUiState by mutableStateOf(EmptyPurchaseUiState())
        private set

    init {
        billingRepository.connectToBilling()

        collectBillingProductsFlow()
    }

    private fun collectBillingProductsFlow() {
        billingRepository.billingProductsFlow
            .onEach { billingState ->
                purchaseUiState = when(billingState) {
                    BillingState.Loading -> LoadingPurchaseUiState()
                    is BillingState.Success -> SuccessfulPurchaseUiState(billingState.productDetailsList)
                    BillingState.Error -> ErrorPurchaseUiState()
                    else -> purchaseUiState
                }
            }.launchIn(viewModelScope)
    }

}