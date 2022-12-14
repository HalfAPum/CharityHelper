package com.narvatov.planthelper.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.halfapum.general.coroutines.exception.collectLatestException
import com.narvatov.planthelper.ui.navigation.BottomBar
import com.narvatov.planthelper.ui.navigation.NavHostContent
import com.narvatov.planthelper.ui.theme.PlanthelperTheme
import kotlin.properties.Delegates.notNull


class MainActivity : ComponentActivity() {

    private var firebaseAnalytics: FirebaseAnalytics by notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            registerForActivityResult(
                ActivityResultContracts.RequestPermission(),
                {},
            ).launch(Manifest.permission.POST_NOTIFICATIONS)
        }

        firebaseAnalytics = Firebase.analytics

        collectLatestException {
            Firebase.crashlytics.recordException(it)

            it.printStackTrace()

            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            true
        }

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