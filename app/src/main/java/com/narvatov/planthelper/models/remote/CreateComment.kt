package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class CreateComment(
    @SerializedName("eventId")
    val eventId: Long,
    @SerializedName("text")
    val text: String,
)
