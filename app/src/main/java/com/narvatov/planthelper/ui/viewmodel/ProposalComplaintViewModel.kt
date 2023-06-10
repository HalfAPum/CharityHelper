package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class ProposalComplaintViewModel(
    private val proposalRepository: ProposalRepository,
): ViewModel() {

    fun sendComplaint(text: String) = launchCatching {
        proposalRepository.sendComplaint(text, NavigationParams.proposalDetailsItemId)

        popBack()
    }
}