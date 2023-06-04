package com.narvatov.planthelper.data.datasource.remote.api

import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.AcceptTransaction
import com.narvatov.planthelper.models.CreateTransaction
import com.narvatov.planthelper.models.CreateTransaction1
import com.narvatov.planthelper.models.remote.CreateComment
import com.narvatov.planthelper.models.remote.NotificationsCheck
import com.narvatov.planthelper.models.remote.UpdateTransactionStatus
import com.narvatov.planthelper.models.remote.UpdateTransactionStatus1
import com.narvatov.planthelper.models.remote.help.EditHelpData
import com.narvatov.planthelper.models.remote.help.UpdateHelpTags
import com.narvatov.planthelper.models.remote.proposal.*
import retrofit2.http.*

interface ProposalApi {

    @POST("api/events/proposal/create")
    suspend fun createProposal(
        @Body createProposal: CreateProposal,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @PUT("api/events/proposal/update/{id}")
    suspend fun editProposal(
        @Path("id") id: Long,
        @Body editHelp: EditHelpData,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @GET("api/events/proposal/get-own")
    suspend fun getOwnProposals(
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    ): ProposalList


    @GET("api/events/proposal/get/{id}")
    suspend fun getProposal(
        @Path("id") id: Long,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    ): ProposalEvent


    @POST("open-api/proposal-search")
    suspend fun getPublicProposals(
        @Body allSearchQuery: AllSearchQuery = AllSearchQuery(),
    ): ProposalSearchList

    @POST("open-api/proposal-search")
    suspend fun getNotPublicProposals(
        @Body allSearchQuery: AllSearchQuery = AllSearchQuery(),
        @Header("Authorization") authHeader: String  = LoginStateHolder.token
    ): ProposalSearchList


    @POST("open-api/proposal-search")
    suspend fun searchProposals(
        @Body searchQuery: SearchQuery,
    ): ProposalSearchList

    @PUT("api/read-notifications")
    suspend fun checkNotifications(
        @Body body: NotificationsCheck,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @POST("api/events/proposal/comment")
    suspend fun addComment(
        @Body commentCreate: CreateComment,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )


    @POST("api/events/proposal/response")
    suspend fun addTransaction(
        @Body commentCreate: CreateTransaction1,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )


    @POST("api/events/proposal/accept/{id}")
    suspend fun acceptTransaction(
        @Path("id") id: Long,
        @Body acceptTransaction: AcceptTransaction,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )


    @POST("api/events/proposal/update-status/{id}")
    suspend fun updateTransactionStatus(
        @Path("id") id: Long,
        @Body updateTransactionStatus: UpdateTransactionStatus,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )
    @POST("api/events/proposal/update-status/{id}")
    suspend fun updateTransactionStatus1(
        @Path("id") id: Long,
        @Body updateTransactionStatus: UpdateTransactionStatus1,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @PUT("api/events/proposal/update/{id}")
    suspend fun updateTransaction(
        @Path("id") id: Long,
        @Body updateTransaction: UpdateTransaction,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @DELETE("api/events/proposal/delete/{id}")
    suspend fun deleteProposal(
        @Path("id") id: Long,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @POST("api/tags/upsert")
    suspend fun updateTags(
        @Body updateTags: UpdateHelpTags,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )


}