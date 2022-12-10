package com.narvatov.planthelper.data.repository

import android.graphics.Bitmap
import com.narvatov.planthelper.data.repository.base.Repository
import org.koin.core.annotation.Factory

@Factory
class PhotoRepository(private val photoManager: com.narvatov.planthelper.data.datasource.filesystem.PhotoManager): Repository() {

    fun saveBitmap(
        oldBitmapPath: String?,
        newBitmap: Bitmap
    ): String {
        photoManager.deletePhoto(oldBitmapPath)

        return photoManager.saveBitmap(newBitmap)
    }

}