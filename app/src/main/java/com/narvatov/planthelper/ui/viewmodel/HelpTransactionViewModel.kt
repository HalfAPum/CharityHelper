package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.RequestStatus
import com.narvatov.planthelper.models.remote.Transaction
import com.narvatov.planthelper.models.remote.help.HelpEvent
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.utils.UnitCallback
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HelpTransactionViewModel(
    private val helpRepository: HelpRepository,
): ErrorViewModel() {

    private val _transactionsFlow = MutableSharedFlow<List<Transaction>>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val transactionFlow = _transactionsFlow.asSharedFlow()
    private val _eventFlow = MutableSharedFlow<HelpEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
            kotlin.runCatching {
                val res = helpRepository.getHelp(NavigationParams.helpDetailsItemId)

                launchCatching {
                    _transactionsFlow.emit(res.transactions)
                }

                launchCatching {
                    _eventFlow.emit(res)
                }
            }
        }
    }

    fun updateTransactionStatus(transaction: Transaction, status: RequestStatus, isApproved: Boolean? = null, callback: UnitCallback = {}) = viewModelScope.launchCatching {
        kotlin.runCatching {
            helpRepository.updateTransaction(
                transaction.copy(transactionStatus = status.nameR),
                isApproved
            )
            callback.invoke()
        }
    }
}