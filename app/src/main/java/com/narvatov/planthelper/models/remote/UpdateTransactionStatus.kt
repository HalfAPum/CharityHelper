package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class UpdateTransactionStatus(
    @SerializedName("status")
    val status: String,
)
data class UpdateTransactionStatus1(
    @SerializedName("status")
    val status: String,
    @SerializedName("filePath")
    val filePath: String,
)
