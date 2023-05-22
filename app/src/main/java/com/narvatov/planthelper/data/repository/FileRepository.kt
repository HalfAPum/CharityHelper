package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.remote.api.FileApi
import com.narvatov.planthelper.data.utils.LoginStateHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.core.annotation.Factory


@Factory
class FileRepository(
    private val fileApi: FileApi,
) {

    suspend fun uploadFile(): String? = withContext(Dispatchers.IO) {
        if (LoginStateHolder.inputStream == null) return@withContext null

        val filePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "file",
            LoginStateHolder.fname,
            RequestBody.create("image/*".toMediaTypeOrNull(), LoginStateHolder.inputStream!!)
        )

        LoginStateHolder.inputStream = null

        fileApi.uploadPicture(filePart).path
    }

}