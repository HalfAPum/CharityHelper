package com.narvatov.planthelper.models

import com.google.gson.annotations.SerializedName

data class CreateTransaction(
    @SerializedName("id")
    val id: Long,
    @SerializedName("comment")
    val comment: String,
)
data class CreateTransaction1(
    @SerializedName("comment")
    val comment: String,
)
