package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class UpdateTransactionStatus(
    @SerializedName("status")
    val status: String
)
