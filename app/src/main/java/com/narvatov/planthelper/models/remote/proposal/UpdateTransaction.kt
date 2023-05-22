package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName

data class UpdateTransaction(
    @SerializedName("id")
    val id: Long,
    @SerializedName("filePath")
    val file: String,
)
