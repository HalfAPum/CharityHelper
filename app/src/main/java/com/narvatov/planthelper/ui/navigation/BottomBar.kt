package com.narvatov.planthelper.ui.navigation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.theme.*

@Composable
fun BottomBar(navController: NavHostController) = Box(modifier = Modifier.border(width = 1.dp, color = SoftGrey)) {
    BottomNavigation(backgroundColor = Color.White) {
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
            val selected = destination.route == selectedRoute

            //Crutch part2 start
            println("NAVIGATOR LOGGER Current top destination is ${backStack.value?.destination?.route}")
            //Crutch part2 end

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(destination.icon),
                        contentDescription = stringResource(destination.text)
                    )
                },
                selectedContentColor = Purple500,
                unselectedContentColor = SuperSoftGrey,
                label = { Text(stringResource(destination.text), maxLines = 1) },
                selected = destination.route == selectedRoute,
                onClick = {
                    if (selected) return@BottomNavigationItem

                    // Navigation strategy
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
}
