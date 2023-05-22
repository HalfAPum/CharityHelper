package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.ProposalDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProposalEventDetailsViewModel(
    private val proposalRepository: ProposalRepository,
): ViewModel() {

    private val _proposalFlow = MutableSharedFlow<ProposalEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val proposalFlow = _proposalFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
            val res = proposalRepository.getProposal(NavigationParams.proposalDetailsItemId)

            _proposalFlow.emit(res)
        }
    }

    fun addComment(id: Long, text: String) = viewModelScope.launchCatching {
        proposalRepository.addComment(id, text)

        UiNavigationEventPropagator.popBack()
        UiNavigationEventPropagator.navigate(ProposalDetails)
    }

}