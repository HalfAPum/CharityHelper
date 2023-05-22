package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName

data class Need(
    @SerializedName("amount")
    var amount: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("unit")
    var unit: String,
)

data class PutNeed(
    @SerializedName("id")
    val id: Int,
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("received")
    var received: Int,
    @SerializedName("receivedTotal")
    val receivedTotal: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("unit")
    val unit: String,
) {
    val receivedComparement: String
        get() = "$receivedTotal/$amount"
}
