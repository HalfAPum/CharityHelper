package com.narvatov.planthelper.models.remote.sign

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.ApiErrorModel
import com.narvatov.planthelper.models.remote.Notification

data class SignUpData(
    @SerializedName("address")
    val address: Address?,
    @SerializedName("companyName")
    val companyName: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("secondName")
    val secondName: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("imagePath")
    val imagePath: String? = null,
    @SerializedName("token")
    val token: String = "",
    @SerializedName("profileImageURL")
    val profileImageUrl: String? = null,
    @SerializedName("refreshToken")
    val refreshToken: String = "",
    @SerializedName("transactionNotifications")
    var transactionNotifications: List<Notification>? = null,
    @SerializedName("id")
    val id: Int = -1,
): ApiErrorModel()

data class Address(
    @SerializedName("city")
    val city: String,
    @SerializedName("district")
    val district: String,
    @SerializedName("homeLocation")
    val homeLocation: String,
    @SerializedName("region")
    val region: String,
)