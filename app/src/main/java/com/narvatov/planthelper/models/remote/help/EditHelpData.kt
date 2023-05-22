package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName

data class EditHelpData(
    @SerializedName("description")
    val description: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("title")
    val title: String,
)