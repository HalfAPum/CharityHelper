package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateTransactionViewModel(
    private val proposalRepository: ProposalRepository,
): ViewModel() {

    fun addTransaction(id: Long, text: String) = viewModelScope.launchCatching {
        proposalRepository.addTransaction(id, text)
    }

}