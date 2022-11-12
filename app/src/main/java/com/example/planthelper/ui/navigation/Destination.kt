package com.example.planthelper.ui.navigation

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BrightnessHigh
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.ListAlt
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.planthelper.ui.navigation.Destination.BottomNavigation

sealed class Destination(val route: String) {

    sealed class BottomNavigation(
        name: String,
        val icon: ImageVector,
        //TODO CHANGE TO STRING RESOURCE
        val text: String,
    ): Destination(name) {

        object Feed : BottomNavigation("Feed", Icons.Rounded.ListAlt, "Feed")

        object Plants : BottomNavigation("Plants", Icons.Rounded.BrightnessHigh, "Plants")

        object Settings : BottomNavigation("Settings", Icons.Rounded.Settings, "Settings")

    }

    object PlantDetails : Destination("PlantDetails")

    object CreatePlant : Destination("CreatePlant")

    object Purchase : Destination("Purchase")
}

val bottomNavigationItems = listOf(
    BottomNavigation.Feed,
    BottomNavigation.Plants,
    BottomNavigation.Settings,
)