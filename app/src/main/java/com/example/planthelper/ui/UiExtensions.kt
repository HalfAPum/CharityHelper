package com.example.planthelper.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun Scaffold(
    navController: NavHostController,
    bottomBar: @Composable (NavHostController) -> Unit = {},
    content: @Composable (NavHostController, PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = { bottomBar(navController) },
        content = { paddingValues ->  content(navController, paddingValues) },
    )
}