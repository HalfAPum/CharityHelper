package com.narvatov.planthelper.data.datasource.remote.api

import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.remote.ImagePathReesponse
import com.narvatov.planthelper.models.remote.help.CreateHelpResponse
import com.narvatov.planthelper.models.remote.help.CreateNeed
import okhttp3.MultipartBody
import retrofit2.http.*

interface FileApi {

    @Multipart
    @POST("open-api/file/")
    suspend fun uploadPicture(
        @Part part: MultipartBody.Part,
    ): ImagePathReesponse

}