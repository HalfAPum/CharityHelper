package com.example.planthelper.ui.navigation

import androidx.annotation.DrawableRes
import com.example.planthelper.R
import com.example.planthelper.ui.navigation.Destination.BottomNavigation

sealed class Destination(val route: String) {

    sealed class BottomNavigation(
        name: String,
        @DrawableRes val icon: Int,
        //TODO CHANGE TO STRING RESOURCE
        val text: String,
    ): Destination(name) {

        object Feed : BottomNavigation("Feed", R.drawable.ic_activity, "Feed")

        object Plants : BottomNavigation("Plants", R.drawable.ic_plant, "Plants")

        object Settings : BottomNavigation("Settings", R.drawable.ic_settings, "Settings")

    }

    object PlantDetails : Destination("PlantDetails")

    object CreatePlant : Destination("CreatePlant")

    object SearchPlantType : Destination("SearchPlantType")

    object Calendar : Destination("Calendar")

    object Purchase : Destination("Purchase")
}

val bottomNavigationItems = listOf(
    BottomNavigation.Feed,
    BottomNavigation.Plants,
    BottomNavigation.Settings,
)