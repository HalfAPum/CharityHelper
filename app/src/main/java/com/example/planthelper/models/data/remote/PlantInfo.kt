package com.example.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class PlantInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("watering")
    val watering: Schedule,
    @SerializedName("fertilizer")
    val fertilizer: Schedule,
    @SerializedName("pruning")
    val pruning: Schedule,
)