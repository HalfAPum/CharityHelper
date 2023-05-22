package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("id")
    val id: Long,
    @SerializedName("eventTitle")
    val eventTitle: String,
    @SerializedName("eventType")
    val eventType: String,
    @SerializedName("newStatus")
    val newStatus: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("isRead")
    var isRead: Boolean,
) {


}
