package com.narvatov.planthelper.ui.navigation

import androidx.annotation.DrawableRes
import com.narvatov.planthelper.R

sealed class Destination(private val baseRoute: String) {

    open val params: List<String> = emptyList()

    val route: String by lazy {
        StringBuilder(baseRoute).run {
            params.forEach { append("{$it}") }
            this.toString()
        }
    }

}

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

object PlantDetails : Destination("PlantDetails/{plantId}") {

    const val PLANT_ID_NAV_PARAM = "plantId"

    override val params = listOf(PLANT_ID_NAV_PARAM)

}

//TODO TEMP NAVIGATION CRUTCH
class PlantDetailsWithParam(route: String): Destination(route)


object CreatePlant : HeaderDestination("CreatePlant", "Tell us about the plant")

object SearchPlantType : HeaderDestination("SearchPlantType", "Search your plant")

object Calendar : Destination("Calendar")

object Purchase : Destination("Purchase")

val bottomNavigationItems = listOf(
    BottomNavigation.Tasks,
    BottomNavigation.Plants,
    BottomNavigation.Settings,
)