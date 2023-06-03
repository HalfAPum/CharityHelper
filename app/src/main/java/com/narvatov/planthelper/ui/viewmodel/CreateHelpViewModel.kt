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
        title: String, description: String, datePicked: String?, datePickedTime: Long?, needs: List<Need>,
        tags: List<Pair<TagTitle, List<String>>>
    ) = viewModelScope.launchPrintingError {
        when {
            title.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.titlecheck))
            }
            description.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.descriptioncheck))
            }
            needs.isEmpty() -> {
                _errorSharedFlow.emit(context.getString(R.string.add_at_least_one_need))
            }
            needs.any { it.title.isEmpty() } -> {
                _errorSharedFlow.emit(context.getString(R.string.needscheck))
            }
            needs.any { it.amount < 1 } -> {
                _errorSharedFlow.emit(context.getString(R.string.amouncheck))
            }
            datePickedTime == null || datePickedTime < System.currentTimeMillis() -> {
                _errorSharedFlow.emit(context.getString(R.string.se))
            }
            else -> {
                helpRepository.createHelp(title, description, datePicked.toEndDate(), needs, tags)

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.publishToaster(context.getString(R.string.createdhelpch))

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.navigate(BottomNavigation.Requests)
            }
        }
    }
}

fun String?.toEndDate() = "${this!!}T23:59:59.999Z"