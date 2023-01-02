package com.narvatov.planthelper.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate

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
        println("NAVIGATOR LOGGER Current top destination is ${backStack.value?.destination?.route}")
        //Crutch part2 end

        BottomNavigationItem(
            icon = { Icon(painter = painterResource(destination.icon), contentDescription = destination.text) },
            label = { Text(destination.text) },
            selected = destination.route == selectedRoute,
            onClick = {
                // First navigation strategy
                // Pop back to destination if it exists in stack
                // Each bottom nav destination have its mini back stack
//                val poppedSuccessfully = navController.popBackStack(destination, inclusive = false)
//
//                if (poppedSuccessfully) return@BottomNavigationItem
//
//                navigate(destination)


                // Second navigation strategy
                // no mini back stack for tabs is left
                // when you navigate to next bottom nav item
                var popNextDestination = false
                bottomNavigationItems.reversed().forEach {
                    if (it.route == destination.route) {
                        popNextDestination = true
                    } else if (popNextDestination) {
                        navController.popBackStack(destination = it, inclusive = false)
                        popNextDestination = false
                    }
                }

                navigate(destination)
            }
        )
    }
}
