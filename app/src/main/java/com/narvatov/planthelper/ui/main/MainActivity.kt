package com.narvatov.planthelper.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.ui.Scaffold
import com.narvatov.planthelper.ui.navigation.BottomBar
import com.narvatov.planthelper.ui.navigation.NavHostContent
import com.narvatov.planthelper.ui.theme.CharityTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CharityTheme {
                val navController = rememberNavController()
                Scaffold(
                    navController = navController,
                    bottomBar = { BottomBar(navController) },
                    content = { _, innerPadding -> NavHostContent(navController, innerPadding) },
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_CODE && resultCode == RESULT_OK) {
            if (data == null) {
                return
            }
            fun Uri.getName(): String {
                val returnCursor = contentResolver.query(this, null, null, null, null)
                val nameIndex = returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                returnCursor.moveToFirst()
                val fileName = returnCursor.getString(nameIndex)
                returnCursor.close()
                return fileName
            }
            LoginStateHolder.fname = data.data?.getName()
            LoginStateHolder.inputStream = data.data?.let { contentResolver.openInputStream(it) }?.readBytes()
        }
    }

}

const val PICK_PHOTO_CODE = 9999