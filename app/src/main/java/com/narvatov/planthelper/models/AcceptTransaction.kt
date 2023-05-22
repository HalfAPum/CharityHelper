package com.narvatov.planthelper.models

import com.google.gson.annotations.SerializedName

data class AcceptTransaction(
    @SerializedName("isAccepted")
    val isAccepted: Boolean
)
