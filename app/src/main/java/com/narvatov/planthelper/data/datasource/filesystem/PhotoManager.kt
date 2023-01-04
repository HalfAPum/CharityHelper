package com.narvatov.planthelper.data.datasource.filesystem

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import org.koin.core.annotation.Factory
import java.io.File


@Factory
class PhotoManager(private val context: Context) {

//    fun saveBitmap(bitmap: Bitmap): String {
//        val filename = System.currentTimeMillis().toString().replace(":", ".") + ".png"
//        val directory = Environment.getExternalStorageDirectory()
//        val destinationFile = File(directory?.path, filename)
//        destinationFile.mkdirs()
//
//        val outStream = FileOutputStream(destinationFile)
//        bitmap.compress(Bitmap.CompressFormat.PNG, 90, outStream)
//        outStream.flush()
//        outStream.close()
//
//        return destinationFile.path
//    }

    //TODO MAKE APPROPRIATE
    fun saveBitmap(bitmap: Bitmap): String {
        val fileName = System.currentTimeMillis().toString().replace(":", ".") + ".jpg"
        val values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, "DCIM/")
            values.put(MediaStore.MediaColumns.IS_PENDING, 1)
        } else {
            val directory =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)
            val file = File(directory, fileName)
            values.put(MediaStore.MediaColumns.DATA, file.absolutePath)
        }
        val uri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        context.contentResolver.openOutputStream(uri!!).use { output ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output)
        }

        return uri.toString()
    }

    //TODO THIS OBVIOUSLY DOESN'T WORK NOW
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