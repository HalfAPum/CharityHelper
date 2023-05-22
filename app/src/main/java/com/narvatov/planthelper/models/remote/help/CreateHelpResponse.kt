package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName

data class CreateHelpResponse(
    @SerializedName("id")
    val id: Int
)
