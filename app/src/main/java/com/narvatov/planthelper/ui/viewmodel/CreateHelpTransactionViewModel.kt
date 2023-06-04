package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.ui.navigation.HelpTransactions
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateHelpTransactionViewModel(
    private val helpRepository: HelpRepository,
): ViewModel() {

    fun addTransaction(id: Long, text: String) = viewModelScope.launchCatching {
        helpRepository.addTransaction(id, text)
        UiNavigationEventPropagator.popBack()
        UiNavigationEventPropagator.popBack()
        UiNavigationEventPropagator.navigate(HelpTransactions)
    }

}