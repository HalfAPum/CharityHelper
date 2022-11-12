package com.example.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class PlantsResponse(
    @SerializedName("plants")
    val plants: List<PlantInfo>
)