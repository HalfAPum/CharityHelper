package com.narvatov.planthelper.models.remote.sign

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.ApiErrorModel

data class SignInData(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
): ApiErrorModel()