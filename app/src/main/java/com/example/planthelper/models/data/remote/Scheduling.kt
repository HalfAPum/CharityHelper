package com.example.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class Scheduling(
    @SerializedName("name")
    val name: String?,
    @SerializedName("schedule")
    val monthSchedule: Map<String, Int>
)