package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class Comment(
    @SerializedName("username")
    val username: String,
    @SerializedName("profileImageURL")
    val profileImageURL: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("creationDate")
    val creationDate: String,
)