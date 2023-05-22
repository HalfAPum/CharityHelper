package com.narvatov.planthelper.ui.viewmodel

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.repository.SignRepository
import com.narvatov.planthelper.models.remote.sign.Address
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.popBack
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.publishToaster
import com.narvatov.planthelper.utils.isInvalidEmail
import com.narvatov.planthelper.utils.isInvalidPassword
import com.narvatov.planthelper.utils.isInvalidPhone
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel(
    private val signRepository: SignRepository,
): ErrorViewModel() {

    val context: Context by com.narvatov.planthelper.utils.inject<Context>()

    fun signUp(
        email: String, password: String, firstName: String, secondName: String,
        telephone: String, companyName: String, address: Address
    ) = viewModelScope.launchPrintingError {
        when {
            email.isInvalidEmail() -> {
                _errorSharedFlow.emit(context.getString(R.string.validemail))
            }
            password.isInvalidPassword() -> {
                _errorSharedFlow.emit(context.getString(R.string.passwordcheck))
            }
            firstName.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.firstnameche))
            }
            secondName.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.secondnameche))
            }
            telephone.isInvalidPhone() -> {
                _errorSharedFlow.emit(context.getString(R.string.phonecheck))
            }
            companyName.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.companynameche))
            }
            address.city.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.citycheck))
            }
            address.district.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.distriche))
            }
            address.homeLocation.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.homeloch))
            }
            address.region.isBlank() -> {
                _errorSharedFlow.emit(context.getString(R.string.regcheck))
            }
            else -> {
                signRepository.userSignUp(address, companyName, email, password, firstName, secondName, telephone)

                popBack()

                publishToaster(context.getString(R.string.signedupsuccess))
            }
        }
    }


}