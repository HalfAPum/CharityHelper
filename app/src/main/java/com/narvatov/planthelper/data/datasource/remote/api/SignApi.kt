package com.narvatov.planthelper.data.datasource.remote.api

import com.narvatov.planthelper.models.remote.ApiErrorModel
import com.narvatov.planthelper.models.remote.sign.SignInData
import com.narvatov.planthelper.models.remote.sign.SignUpData
import com.narvatov.planthelper.models.remote.sign.SignUpDataL
import retrofit2.http.Body
import retrofit2.http.POST

interface SignApi {

    @POST("auth/sign-in")
    suspend fun userSignIn(@Body signInData: SignInData): SignUpDataL

    @POST("auth/sign-up")
    suspend fun userSignUp(@Body signUpData: SignUpData): ApiErrorModel

}