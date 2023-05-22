package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("username")
    val username: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("phoneNumber")
    val phone: String,
    @SerializedName("profileImageURL")
    val profileImageURL: String,
)