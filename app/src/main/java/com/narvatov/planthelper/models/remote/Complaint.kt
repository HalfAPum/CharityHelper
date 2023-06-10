package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class Complaint(
    @SerializedName("description")
    val description: String,
    @SerializedName("eventID")
    val eventID: Long,
    @SerializedName("eventType")
    val eventType: String,
)
