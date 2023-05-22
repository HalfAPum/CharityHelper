package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.R
import com.narvatov.planthelper.data.datasource.remote.api.SignApi
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.data.utils.SignInState
import com.narvatov.planthelper.models.remote.sign.Address
import com.narvatov.planthelper.models.remote.sign.SignInData
import com.narvatov.planthelper.models.remote.sign.SignUpData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import org.koin.core.annotation.Factory

@Factory
class SignRepository(
    private val signApi: SignApi,
    private val fileRepository: FileRepository,
) : Repository() {

    suspend fun userSignIn(email: String, password: String) = errorCall(R.string.wrong_credentials) {
        withContext(Dispatchers.IO) {
            val signUpData = signApi.userSignIn(SignInData(email, password))

            LoginStateHolder.signInState = SignInState.User(signUpData)
            signUpData.transactionNotifications?.let { LoginStateHolder.notificationList = it }
        }
    }

    suspend fun userSignUp(
        address: Address, companyName: String, email: String, password: String,
        firstName: String,secondName: String, telephone: String
    ) = withContext(Dispatchers.IO) {
        val image = fileRepository.uploadFile()

        val result = signApi.userSignUp(SignUpData(
            address, companyName, email, password, firstName, secondName, telephone,
            imagePath = image
        ))

        if (result.error != null) throw IOException(result.error)
    }

}