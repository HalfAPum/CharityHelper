package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.help.HelpEvent
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.ui.navigation.HelpDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HelpEventDetailsViewModel(
    private val helpRepository: HelpRepository,
): ViewModel() {

    private val _helpFlow = MutableSharedFlow<HelpEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val helpFlow = _helpFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
            val res = helpRepository.getHelp(NavigationParams.helpDetailsItemId)

            _helpFlow.emit(res)
        }
    }

    fun addComment(id: Long, text: String) = viewModelScope.launchCatching {
        helpRepository.addComment(id, text)

        UiNavigationEventPropagator.popBack()
        UiNavigationEventPropagator.navigate(HelpDetails)
    }
}