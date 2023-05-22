package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Tag

data class CreateNeed(
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("needs")
    val needs: List<Need>,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("imagePath")
    val image: String?,
)
