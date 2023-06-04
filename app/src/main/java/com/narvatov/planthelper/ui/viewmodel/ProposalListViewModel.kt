package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.models.remote.SortBy
import com.narvatov.planthelper.models.remote.SortOrder
import com.narvatov.planthelper.models.remote.Status
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel

@OptIn(FlowPreview::class)
@KoinViewModel
class ProposalListViewModel(
    private val proposalRepository: ProposalRepository,
): ViewModel() {

    var searchSavedStr = MutableStateFlow("")

    private val _ownProposalSharedFlow = MutableSharedFlow<List<ProposalEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val ownProposalSharedFlow = _ownProposalSharedFlow.asSharedFlow()

    private val _publicProposalSharedFlow = MutableSharedFlow<List<ProposalEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val publicProposalSharedFlow = _publicProposalSharedFlow.asSharedFlow()

    private val _searchProposalSharedFlow = MutableSharedFlow<List<ProposalEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val searchProposalSharedFlow = _searchProposalSharedFlow.asSharedFlow()


    private val debounceFlow = MutableSharedFlow<String>()

    init {
        viewModelScope.launchCatching {
            val ownProposals = proposalRepository.getOwnProposals()

            _ownProposalSharedFlow.emit(ownProposals?.proposalEvents ?: emptyList())
        }

        viewModelScope.launchCatching {
            val publicProposals = proposalRepository.getPublicProposals()

            _searchProposalSharedFlow.emit(publicProposals.proposalEvents)
        }
        viewModelScope.launchCatching {

            val notpublicProposals = proposalRepository.getNotPublicProposals()

            _publicProposalSharedFlow.emit(notpublicProposals.proposalEvents)
        }

        viewModelScope.launchCatching {
            debounceFlow.debounce(1000L).collectLatest { query ->
                val result = proposalRepository.searchProposals(query, "", "", "", emptyList())

                _searchProposalSharedFlow.emit(result.proposalEvents)
            }
        }
    }

    fun runInit() {
        viewModelScope.launchCatching {
            val ownProposals = proposalRepository.getOwnProposals()

            _ownProposalSharedFlow.emit(ownProposals?.proposalEvents ?: emptyList())
        }

        viewModelScope.launchCatching {
            val publicProposals = proposalRepository.getPublicProposals()
            val notpublicProposals = proposalRepository.getNotPublicProposals()

            _searchProposalSharedFlow.emit(publicProposals.proposalEvents)
            _publicProposalSharedFlow.emit(notpublicProposals.proposalEvents)
        }

    }

    fun search(query: String, order: SortOrder, sortField: SortBy, status: Status?, tags: List<Pair<TagTitle, List<String>>>) = viewModelScope.launchCatching {
        val result = proposalRepository.searchProposals(query, order.nameR, sortField.nameR, status?.nameR, tags)

        _searchProposalSharedFlow.emit(result.proposalEvents)
    }


    fun emitSearchState(query: String) = viewModelScope.launchCatching {
        debounceFlow.emit(query)
    }

}