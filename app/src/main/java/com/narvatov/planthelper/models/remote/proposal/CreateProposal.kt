package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Tag

data class CreateProposal(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("maxConcurrentRequests")
    val maxConcurrentRequests: Long,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("imagePath")
    val image: String?,
)
