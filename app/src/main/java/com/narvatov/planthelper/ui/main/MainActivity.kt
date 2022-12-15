package com.narvatov.planthelper.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.narvatov.planthelper.ui.Scaffold
import com.narvatov.planthelper.ui.main.delegate.exception.CoroutineExceptionCollector
import com.narvatov.planthelper.ui.main.delegate.exception.ICoroutineExceptionCollector
import com.narvatov.planthelper.ui.main.delegate.firebase.FirebaseDelegate
import com.narvatov.planthelper.ui.main.delegate.firebase.IFirebaseDelegate
import com.narvatov.planthelper.ui.main.delegate.permission.IPermissionProvider
import com.narvatov.planthelper.ui.main.delegate.permission.PermissionProvider
import com.narvatov.planthelper.ui.navigation.BottomBar
import com.narvatov.planthelper.ui.navigation.NavHostContent
import com.narvatov.planthelper.ui.theme.PlanthelperTheme


class MainActivity : ComponentActivity(),
    IFirebaseDelegate by FirebaseDelegate,
    IPermissionProvider by PermissionProvider,
    ICoroutineExceptionCollector by CoroutineExceptionCollector
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initFirebase()
        checkNotificationPermission()
        initCoroutineExceptionCollector()

        setContent {
            PlanthelperTheme {
                val navController = rememberNavController()
                Scaffold(
                    navController = navController,
                    bottomBar = { BottomBar(navController) },
                    content = { _, innerPadding -> NavHostContent(navController, innerPadding) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    val navController = rememberNavController()
    Scaffold(
        navController = navController,
        bottomBar = { BottomBar(navController) },
        content = { _, innerPadding -> NavHostContent(navController, innerPadding) },
    )
}