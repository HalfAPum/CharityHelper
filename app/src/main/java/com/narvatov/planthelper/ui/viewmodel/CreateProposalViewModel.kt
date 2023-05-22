package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.publishToaster
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateProposalViewModel(
    private val proposalRepository: ProposalRepository,
): ErrorViewModel() {
    val context: Context by com.narvatov.planthelper.utils.inject<Context>()


    fun createProposal(
        title: String, description: String, maxConcurrentRequests: Long,
        tags: List<Pair<TagTitle, List<String>>>
    ) = viewModelScope.launchPrintingError {
        when {
            title.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.titlecheck))
            }
            description.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.descriptioncheck))
            }
            maxConcurrentRequests < 1 -> {
                _errorSharedFlow.emit(context.getString(R.string.concurrentrequests))
            }
            else -> {
                proposalRepository.createProposal(title, description, maxConcurrentRequests, tags)

                popBack()

                publishToaster(context.getString(R.string.createproposalch))

                popBack()

                navigate(BottomNavigation.Proposals)
            }
        }
    }
}