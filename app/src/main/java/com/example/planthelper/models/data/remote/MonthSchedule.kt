package com.example.planthelper.models.data.remote

import com.google.gson.annotations.SerializedName

data class MonthSchedule(
    @SerializedName("June")
    val june: Int = 0,
    @SerializedName("July")
    val july: Int = 0,
    @SerializedName("August")
    val august: Int = 0,
    @SerializedName("September")
    val september: Int = 0,
    @SerializedName("October")
    val october: Int = 0,
    @SerializedName("November")
    val november: Int = 0,
    @SerializedName("December")
    val december: Int = 0,
    @SerializedName("January")
    val january: Int = 0,
    @SerializedName("February")
    val february: Int = 0,
    @SerializedName("March")
    val march: Int = 0,
    @SerializedName("April")
    val april: Int = 0,
    @SerializedName("May")
    val may: Int = 0,
)