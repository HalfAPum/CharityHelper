package com.narvatov.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class PlantInfo(
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("watering")
    val watering: Scheduling,
    @SerializedName("fertilizer")
    val fertilizer: Scheduling,
    @SerializedName("pruning")
    val pruning: Scheduling,
)