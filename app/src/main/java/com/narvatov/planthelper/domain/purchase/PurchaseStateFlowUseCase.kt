package com.narvatov.planthelper.domain.purchase

import com.narvatov.planthelper.data.repository.billing.BillingConnector
import com.narvatov.planthelper.models.ui.purchase.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory

@Factory
class PurchaseStateFlowUseCase(
    private val billingConnector: BillingConnector,
) {

    operator fun invoke(): Flow<PurchaseUiState> {
        return billingConnector.billingConnectionFlow.map { billingState ->
            when(billingState) {
                BillingState.Loading -> LoadingPurchaseUiState()
                is BillingState.Success -> SuccessfulPurchaseUiState(billingState)
                is BillingState.Error -> ErrorPurchaseUiState(billingState.errorMessage)
                else -> ErrorPurchaseUiState("Unexpected billing error happened visit" +
                        " this page later or check your google play account")
            }
        }
    }

}