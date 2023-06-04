package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.RequestStatus
import com.narvatov.planthelper.models.remote.Transaction
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.utils.UnitCallback
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TransactionViewModel(
    private val proposalRepository: ProposalRepository,
): ErrorViewModel() {

    private val _transactionsFlow = MutableSharedFlow<List<Transaction>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val transactionFlow = _transactionsFlow.asSharedFlow()
    private val _eventFlow = MutableSharedFlow<ProposalEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
                val res = proposalRepository.getProposal(NavigationParams.proposalDetailsItemId)

//                launchCatching {
//                    _transactionsFlow.emit(res.transactions)
//                }

                launchCatching {
                    _eventFlow.emit(res)
                }
        }
    }

    fun accept(id: Long) = viewModelScope.launchCatching {
        proposalRepository.acceptTransaction(id)
    }

    fun reject(id: Long) = viewModelScope.launchCatching {
        proposalRepository.rejectTransaction(id)
    }

    fun updateTransactionStatus(id: Long, status: RequestStatus, callback: UnitCallback = {}) = viewModelScope.launchCatching {
            proposalRepository.updateTransactionStatus(id, status)
            callback.invoke()
    }
}