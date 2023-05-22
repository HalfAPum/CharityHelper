package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName

open class ApiErrorModel {
    @SerializedName("error")
    val error: String? = null
}