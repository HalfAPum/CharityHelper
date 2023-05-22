package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class NotificationsCheck(
    @SerializedName("IDs")
    val ids: List<Long>
)
