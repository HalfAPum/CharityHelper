package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.HelpRepository
import com.narvatov.planthelper.data.utils.NavigationParams
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HelpComplaintViewModel(
    private val helpRepository: HelpRepository,
) : ViewModel() {

    fun sendComplaint(text: String) = launchCatching {
        helpRepository.sendComplaint(text, NavigationParams.helpDetailsItemId)

        popBack()
    }
}