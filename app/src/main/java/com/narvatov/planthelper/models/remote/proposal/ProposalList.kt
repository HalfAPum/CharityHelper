package com.narvatov.planthelper.models.remote.proposal

import com.google.gson.annotations.SerializedName
import com.narvatov.planthelper.models.remote.Author
import com.narvatov.planthelper.models.remote.Comment
import com.narvatov.planthelper.models.remote.Tag
import com.narvatov.planthelper.models.remote.Transaction

data class ProposalList(
    @SerializedName("proposalEvents")
    val proposalEvents: List<ProposalEvent>
)

data class ProposalSearchList(
    @SerializedName("items")
    val proposalEvents: List<ProposalEvent>
)

data class ProposalEvent(
    @SerializedName("id")
    val id: Long,
    @SerializedName("authorInfo")
    val author: Author,
    @SerializedName("availableHelps")
    val availableHelps: String,
    @SerializedName("maxConcurrentRequests")
    val maxConcurrentRequests: Long,
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
    @SerializedName("competitionDate")
    val competitionDate: String,
    @SerializedName("creationDate")
    val creationDate: String,
    @SerializedName("transactions")
    val transactions: List<Transaction>,
) {

    val busyRequestText: String
        get() = "${((maxConcurrentRequests - (kotlin.runCatching { availableHelps.toLong() }
            .getOrNull()?: 0) ))}/$maxConcurrentRequests"
}
