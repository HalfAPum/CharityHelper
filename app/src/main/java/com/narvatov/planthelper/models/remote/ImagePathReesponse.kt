package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

data class ImagePathReesponse(
    @SerializedName("path")
    val path: String,
)