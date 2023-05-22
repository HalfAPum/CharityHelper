package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Tag

data class UpdateHelpTags(
    @SerializedName("eventID")
    val eventId: Int,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("eventType")
    val eventType: String = "help",
)