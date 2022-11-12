package com.example.planthelper.data.repository

import android.graphics.Bitmap
import com.example.planthelper.data.datasource.filesystem.PhotoManager
import com.example.planthelper.data.repository.base.Repository
import org.koin.core.annotation.Factory

@Factory
class PhotoRepository(private val photoManager: PhotoManager): Repository() {

    fun saveBitmap(
        oldBitmapPath: String?,
        newBitmap: Bitmap
    ): String {
        photoManager.deletePhoto(oldBitmapPath)

        return photoManager.saveBitmap(newBitmap)
    }

}