package com.narvatov.planthelper.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.halfapum.general.coroutines.exception.collectLatestException
import com.narvatov.planthelper.ui.navigation.BottomBar
import com.narvatov.planthelper.ui.navigation.NavHostContent
import com.narvatov.planthelper.ui.theme.PlanthelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectLatestException {

            println("$it")
            Log.e("FUCK", "$it")
            it.stackTrace.forEach {
                Log.e("FUCK", "${it}")
            }
            println(it.stackTrace)

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