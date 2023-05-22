package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName

data class UpdateHelpTransaction(
    @SerializedName("id")
    val id: Long,
    @SerializedName("status")
    val status: String,
    @SerializedName("needs")
    val putNeeds: List<PutNeed>,
    @SerializedName("filePath")
    val file: String?,
    @SerializedName("isApproved")
    val isApproved: Boolean?
)