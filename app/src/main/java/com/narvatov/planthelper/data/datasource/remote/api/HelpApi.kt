package com.narvatov.planthelper.data.datasource.remote.api

import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.AcceptTransaction
import com.narvatov.planthelper.models.CreateTransaction
import com.narvatov.planthelper.models.CreateTransaction1
import com.narvatov.planthelper.models.remote.Complaint
import com.narvatov.planthelper.models.remote.CreateComment
import com.narvatov.planthelper.models.remote.NotificationsCheck
import com.narvatov.planthelper.models.remote.UpdateTransactionStatus
import com.narvatov.planthelper.models.remote.help.*
import com.narvatov.planthelper.models.remote.proposal.*
import retrofit2.http.*

interface HelpApi {

    @POST("api/complaint")
    suspend fun sendComplaint(
        @Body complaint: Complaint,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )
    @POST("api/events/help/create")
    suspend fun createHelp(
        @Body createHelp: CreateNeed,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    ): CreateHelpResponse

    @PUT("api/events/help/{id}")
    suspend fun editHelp(
        @Path("id") id: Long,
        @Body editHelp: EditHelpData,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @GET("api/events/help/own")
    suspend fun getOwnHelps(
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    ): OwnHelpEvents


    @POST("open-api/help-search")
    suspend fun getPublicHelps(
        @Body allSearchQuery: AllSearchQuery = AllSearchQuery(),
    ): SearchHelpEvents

    @POST("open-api/help-search")
    suspend fun getNotPublicHelps(
        @Body allSearchQuery: AllSearchQuery = AllSearchQuery(),
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    ): SearchHelpEvents


    @POST("open-api/help-search")
    suspend fun searchHelps(
        @Body searchQuery: SearchQuery,
    ): SearchHelpEvents


    @POST("api/events/help/comment")
    suspend fun addComment(
        @Body commentCreate: CreateComment,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @POST("api/tags/upsert")
    suspend fun updateTags(
        @Body updateTags: UpdateHelpTags,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )


    @POST("api/events/help/response")
    suspend fun addTransaction(
        @Body createHelpTransaction: CreateTransaction1,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )

    @PUT("api/events/help/transaction")
    suspend fun updateTransaction(
        @Body updateHelpTransaction: UpdateHelpTransaction,
        @Header("Authorization") authHeader: String = LoginStateHolder.token
    )
}