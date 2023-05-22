package com.narvatov.planthelper.data.utils

import androidx.compose.runtime.mutableStateOf
import com.narvatov.planthelper.models.remote.Notification
import com.narvatov.planthelper.models.remote.sign.SignUpData
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.InputStream

object LoginStateHolder {
    var signInState: SignInState = SignInState.None
    val token: String
        get() = "Bearer ${signInState.signInData?.token}"
    val isLoggedIn: Boolean
        get() = signInState.isLoggedIn

    var fname: String? = null
    var inputStream: ByteArray? = null

    var notificationList: List<Notification>? = null

}

sealed class SignInState(val signInData: SignUpData?) {
    object None: SignInState(null)
    data class User(val signUpData: SignUpData): SignInState(signUpData)

    val isLoggedIn: Boolean
        get() = signInData != null
}