package com.example.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class Schedule(
    @SerializedName("name")
    val name: String?,
    @SerializedName("schedule")
    val monthSchedule: MonthSchedule
)