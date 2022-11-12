package com.example.planthelper.ui.navigation

import androidx.annotation.DrawableRes
import com.example.planthelper.R
import com.example.planthelper.ui.navigation.Destination.BottomNavigation

sealed class Destination(val route: String) {

    sealed class HeaderDestination(route: String, val headerText: String) : Destination(route)

    sealed class BottomNavigation(
        name: String,
        headerText: String,
        @DrawableRes val icon: Int,
        //TODO CHANGE TO STRING RESOURCE
        val text: String,
    ): HeaderDestination(name, headerText) {

        object Tasks : BottomNavigation(
            name = "Tasks",
            headerText = "My tasks",
            icon = R.drawable.ic_activity,
            text = "Tasks"
        )

        object Plants : BottomNavigation(
            name = "Plants",
            headerText = "My plants list",
            icon = R.drawable.ic_plant,
            text = "Plants"
        )

        object Settings : BottomNavigation(
            name = "Settings",
            headerText = "Settings",
            icon = R.drawable.ic_settings,
            text = "Settings"
        )

    }

    object PlantDetails : Destination("PlantDetails")

    object CreatePlant : HeaderDestination("CreatePlant", "Tell us about the plant")

    object SearchPlantType : HeaderDestination("SearchPlantType", "Search your plant")

    object Calendar : Destination("Calendar")

    object Purchase : Destination("Purchase")
}

val bottomNavigationItems = listOf(
    BottomNavigation.Tasks,
    BottomNavigation.Plants,
    BottomNavigation.Settings,
)