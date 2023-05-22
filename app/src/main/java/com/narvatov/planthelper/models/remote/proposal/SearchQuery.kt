package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Tag

data class SearchQuery(
    @SerializedName("name")
    val query: String,
    @SerializedName("order")
    val order: String,
    @SerializedName("sortField")
    val sortField: String,
    @SerializedName("statusStates")
    val status: String? = null,
    @SerializedName("tags")
    val tags: List<Tag>,
)
