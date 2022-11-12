package com.example.planthelper.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.planthelper.ui.navigation.BottomBar
import com.example.planthelper.ui.navigation.NavHostContent
import com.example.planthelper.ui.theme.PlanthelperTheme
import com.halfapum.general.coroutines.exception.collectLatestException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        collectLatestException {
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