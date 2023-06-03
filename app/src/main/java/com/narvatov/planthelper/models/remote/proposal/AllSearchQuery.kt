package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName

data class AllSearchQuery(
    @SerializedName("takingPart")
    val takingPart: Boolean = true,
    @SerializedName("pageNumber")
    val pageNumber: Int = 1,
    @SerializedName("pageSize")
    val pageSize: Int = 100,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("sortField")
    val sortField: String = "creation_date",
    @SerializedName("order")
    val order: String = "desc",
    @SerializedName("tags")
    val tags: List<String> = emptyList(),
)