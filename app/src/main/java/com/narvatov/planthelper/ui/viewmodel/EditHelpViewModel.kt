package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.models.remote.TagTitle
import com.narvatov.planthelper.models.remote.help.HelpEvent
import com.narvatov.planthelper.ui.navigation.HelpDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class EditHelpViewModel(
    private val helpRepository: HelpRepository,
): ErrorViewModel() {

    val context: Context by com.narvatov.planthelper.utils.inject<Context>()

    private val _helpFlow = MutableSharedFlow<HelpEvent>(1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val helpFlow = _helpFlow.asSharedFlow()

    init {
        viewModelScope.launchCatching {
            val res = helpRepository.getHelp(NavigationParams.helpDetailsItemId)

            _helpFlow.emit(res)
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
                helpRepository.editHelp(title, description, status, tags)

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.publishToaster(context.getString(R.string.helpedithc))

                UiNavigationEventPropagator.popBack()

                UiNavigationEventPropagator.navigate(HelpDetails)
            }
        }
    }
}