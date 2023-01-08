package com.narvatov.planthelper.ui.viewmodel

import android.app.Activity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.BillingFlowParams
import com.narvatov.planthelper.data.repository.BillingRepository
import com.narvatov.planthelper.data.utils.logBilling
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

        collectPurchasedProductsFlow()
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

    private fun collectPurchasedProductsFlow() {
        billingRepository.purchasedProductsFlow
            .onEach { purchasedList ->
                purchaseUiState = purchaseUiState.copy(purchasedList = purchasedList)
            }.launchIn(viewModelScope)
    }

    fun launchBillingFlow(activity: Activity, billingFlowParams: BillingFlowParams) {
        billingRepository.launchBillingFlow(activity, billingFlowParams)
    }

}