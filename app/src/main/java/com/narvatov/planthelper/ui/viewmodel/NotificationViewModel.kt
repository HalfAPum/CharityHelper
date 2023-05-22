package com.narvatov.planthelper.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.data.repository.ProposalRepository
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.Notification
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class NotificationViewModel(
    private val proposalRepository: ProposalRepository,
): ViewModel() {

    fun checkNotification(id: Long) = viewModelScope.launchCatching {
        proposalRepository.checkNotification(id)
    }

}