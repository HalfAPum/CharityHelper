package com.narvatov.planthelper.data.datasource.filesystem

import android.content.Context
import android.graphics.Bitmap
import org.koin.core.annotation.Factory
import java.io.File
import java.io.FileOutputStream


@Factory
class PhotoManager(private val context: Context) {

    fun saveBitmap(bitmap: Bitmap): String {
        val filename = System.currentTimeMillis().toString().replace(":", ".") + ".png"
        val directory = context.applicationContext.getExternalFilesDir(null)
        val destinationFile = File(directory?.path, filename)

        val outStream = FileOutputStream(destinationFile)
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream)
        outStream.flush()
        outStream.close()

        return destinationFile.path
    }

    fun deletePhoto(path: String?) = path?.let {
        val file = File(path)
        file.delete()

        if (file.exists()) {
            file.canonicalFile.delete()
            if (file.exists()) {
                context.deleteFile(file.name)
            }
        }
    }

}