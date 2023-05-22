package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("title")
    val title: String,
    @SerializedName("values")
    val values: List<String>,
//    @SerializedName("eventType")
//    val eventType: String = "proposal-event",
)
