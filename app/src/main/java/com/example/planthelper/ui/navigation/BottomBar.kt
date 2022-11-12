package com.example.planthelper.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBar(navController: NavHostController) = BottomNavigation {
    /**
     * This lines needed only to update composable when nev destination moves to top.
     */
    //Crutch part1 start
    val backStack = navController.currentBackStackEntryAsState()
    //Crutch part1 end

    val selectedRoute = navController.backQueue.asReversed().firstOrNull { entry ->
        bottomNavigationItems.any { bottomNavItem ->
            entry.destination.route == bottomNavItem.route
        }
    }?.destination?.route

    bottomNavigationItems.forEach { destination ->
        //Crutch part2 start
        println("Current top destination is ${backStack.value?.destination?.route}")
        //Crutch part2 end

        BottomNavigationItem(
            icon = { Icon(painter = painterResource(destination.icon), contentDescription = destination.text) },
            label = { Text(destination.text) },
            selected = destination.route == selectedRoute,
            onClick = {
                navController.navigate(destination.route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}
