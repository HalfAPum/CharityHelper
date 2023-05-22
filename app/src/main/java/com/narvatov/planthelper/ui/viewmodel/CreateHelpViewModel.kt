package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.help.Need
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateHelpViewModel(
    private val helpRepository: HelpRepository,
): ErrorViewModel() {

    val context: Context by com.narvatov.planthelper.utils.inject<Context>()

    fun createHelp(
        title: String, description: String, needs: List<Need>,
        tags: List<Pair<TagTitle, List<String>>>
    ) = viewModelScope.launchPrintingError {
        when {
            title.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.titlecheck))
            }
            description.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.descriptioncheck))
            }
            needs.any { it.title.isEmpty() } -> {
                _errorSharedFlow.emit(context.getString(R.string.needscheck))
            }
            needs.any { it.amount < 0 } -> {
                _errorSharedFlow.emit(context.getString(R.string.amouncheck))
            }
            else -> {
                helpRepository.createHelp(title, description, needs, tags)

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.publishToaster(context.getString(R.string.createdhelpch))

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.navigate(BottomNavigation.Requests)
            }
        }
    }
}