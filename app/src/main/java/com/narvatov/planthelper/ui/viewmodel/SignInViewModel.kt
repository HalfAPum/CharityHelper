package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.SignRepository
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.utils.isInvalidEmail
import com.narvatov.planthelper.utils.isInvalidPassword
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel(
    private val signRepository: SignRepository,
): ErrorViewModel() {

    val context: Context by com.narvatov.planthelper.utils.inject<Context>()

    fun signIn(email: String, password: String) = viewModelScope.launchPrintingError {
        when {
            email.isInvalidEmail() -> {
                _errorSharedFlow.emit(context.getString(R.string.validemail))
            }
            password.isInvalidPassword() -> {
                _errorSharedFlow.emit(context.getString(R.string.passwordcheck))
            }
            else -> {
                viewModelScope.launchPrintingError {
                    signRepository.userSignIn(email, password)

                    popBack()

                    navigate(BottomNavigation.Account)
                }
            }
        }
    }

}