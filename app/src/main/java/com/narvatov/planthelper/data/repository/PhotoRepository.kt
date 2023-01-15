package com.narvatov.planthelper.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.narvatov.planthelper.data.datasource.filesystem.PhotoManager
import com.narvatov.planthelper.data.repository.base.Repository
import org.koin.core.annotation.Factory

@Factory
class PhotoRepository(
    private val context: Context,
    private val photoManager: PhotoManager,
): Repository() {

    suspend fun savePlantImage(
        photo: Bitmap?,
        defaultImage: String,
        oldPhotoPath: String?,
    ) : String {
        val bitmap = photo ?: defaultImage.getBitmap()

        return saveBitmap(
            oldBitmapPath = oldPhotoPath,
            newBitmap = bitmap,
        )
    }

    private suspend fun String.getBitmap(): Bitmap {
        val loader = ImageLoader(context)
        val request = ImageRequest.Builder(context)
            .data(this)
            .allowHardware(false)
            .build()

        val result = (loader.execute(request) as SuccessResult).drawable

        return (result as BitmapDrawable).bitmap
    }

    private fun saveBitmap(
        oldBitmapPath: String?,
        newBitmap: Bitmap
    ): String {
        photoManager.deletePhoto(oldBitmapPath)

        return photoManager.saveBitmap(newBitmap)
    }

}