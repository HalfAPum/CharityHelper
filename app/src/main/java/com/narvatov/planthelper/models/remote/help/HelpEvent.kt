package com.narvatov.planthelper.models.remote.help

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Author
import com.narvatov.planthelper.models.remote.Comment
import com.narvatov.planthelper.models.remote.Tag
import com.narvatov.planthelper.models.remote.Transaction

data class OwnHelpEvents(
    @SerializedName("events")
    val helpEvents: List<HelpEvent>,
)

data class SearchHelpEvents(
    @SerializedName("items")
    val helpEvents: List<HelpEvent>,
)

data class HelpEvent(
    @SerializedName("id")
    val id: Long,
    @SerializedName("authorInfo")
    val author: Author,
    @SerializedName("comments")
    val commentList: List<Comment>,
    @SerializedName("tags")
    val tags: List<Tag>,
    @SerializedName("status")
    val status: String,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("endDate")
    val competitionDate: String,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("transactions")
    val transactions: List<Transaction>,
    @SerializedName("needs")
    val needs: List<PutNeed>,
)
