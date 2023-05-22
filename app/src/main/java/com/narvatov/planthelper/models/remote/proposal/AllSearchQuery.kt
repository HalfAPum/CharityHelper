package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName

data class AllSearchQuery(
    @SerializedName("takingPart")
    val takingPart: Boolean = true,
)