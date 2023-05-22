package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.models.remote.SortBy
import com.narvatov.planthelper.models.remote.SortOrder
import com.narvatov.planthelper.models.remote.Status
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.help.HelpEvent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.koin.android.annotation.KoinViewModel

@OptIn(FlowPreview::class)
@KoinViewModel
class HelpListViewModel(
    private val helpRepository: HelpRepository,
): ViewModel() {

    var searchSavedStr = MutableStateFlow("")

    private val _ownHelpSharedFlow = MutableSharedFlow<List<HelpEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val ownHelpSharedFlow = _ownHelpSharedFlow.asSharedFlow()

    private val _publicHelpSharedFlow = MutableSharedFlow<List<HelpEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val publicHelpSharedFlow = _publicHelpSharedFlow.asSharedFlow()

    private val _searchHelpSharedFlow = MutableSharedFlow<List<HelpEvent>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val searchHelpSharedFlow = _searchHelpSharedFlow.asSharedFlow()


    private val debounceFlow = MutableSharedFlow<String>()

    init {
        viewModelScope.launchCatching {
            val ownProposals = helpRepository.getOwnHelps()

            _ownHelpSharedFlow.emit(ownProposals.helpEvents)
        }

        viewModelScope.launchCatching {
            val publicProposals = helpRepository.getPublicHelps()

            _publicHelpSharedFlow.emit(publicProposals.helpEvents)
            _searchHelpSharedFlow.emit(publicProposals.helpEvents)
        }

        viewModelScope.launchCatching {
            debounceFlow.debounce(1000L).collectLatest { query ->
                val result = helpRepository.searchHelps(query, "", "", "", emptyList())

                _searchHelpSharedFlow.emit(result.helpEvents)
            }
        }
    }

    fun search(query: String, order: SortOrder, sortField: SortBy, status: Status?, tags: List<Pair<TagTitle, List<String>>>) = viewModelScope.launchCatching {
        val result = helpRepository.searchHelps(query, order.nameR, sortField.nameR, status?.nameR, tags)

        _searchHelpSharedFlow.emit(result.helpEvents)
    }


    fun emitSearchState(query: String) = viewModelScope.launchCatching {
        debounceFlow.emit(query)
    }

}