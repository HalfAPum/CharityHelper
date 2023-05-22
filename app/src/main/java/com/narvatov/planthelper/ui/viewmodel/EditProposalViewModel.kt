package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.proposal.ProposalEvent
import com.narvatov.planthelper.ui.navigation.HelpDetails
import com.narvatov.planthelper.ui.navigation.ProposalDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EditProposalViewModel(
    private val proposalRepository: ProposalRepository,
): ErrorViewModel() {

    val context: Context by com.narvatov.planthelper.utils.inject<Context>()

    private val _proposalFlow = MutableSharedFlow<ProposalEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val proposalFlow = _proposalFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
            val res = proposalRepository.getProposal(NavigationParams.proposalDetailsItemId)

            _proposalFlow.emit(res)
        }
    }

    fun editHelp(
        title: String, description: String, status: String,
        tags: List<Pair<TagTitle, List<String>>>
    ) = viewModelScope.launchPrintingError {
        when {
            title.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.titlecheck))
            }
            description.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.descriptioncheck))
            }
            else -> {
                proposalRepository.editProposal(title, description, status, tags)

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.publishToaster(context.getString(R.string.editedproposalsucce))

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.navigate(ProposalDetails)
            }
        }
    }
}