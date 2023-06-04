package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName

data class UpdateTransaction(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("filePath")
    val file: String,
)
