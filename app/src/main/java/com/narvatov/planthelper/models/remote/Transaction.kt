package com.narvatov.planthelper.models.remote

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.help.PutNeed

data class Transaction(
    @SerializedName("comment")
    val comment: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("creator", alternate = ["receiver"])
    val creator: Author,
    @SerializedName("responder")
    val responder: Author,
    @SerializedName("transactionStatus")
    val transactionStatus: String,
    @SerializedName("reportURL")
    val reportURL: String? = null,
    @SerializedName("needs")
    val putNeeds: List<PutNeed> = emptyList(),
    @SerializedName("isApproved")
    val isApproved: Boolean = false
)