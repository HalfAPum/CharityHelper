package com.narvatov.planthelper.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

fun NavGraphBuilder.bottomNavigation(builder: NavGraphBuilder.() -> Unit) {
    builder()
}

fun NavGraphBuilder.composable(
    destination: Destination,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = emptyList(),
        deepLinks = emptyList(),
        content = content,
    )
}

fun NavGraphBuilder.composable(
    destination: HeaderDestination,
    showHeader: Boolean = true,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.route,
        arguments = emptyList(),
        deepLinks = emptyList(),
        content = {
            Column {
                if (showHeader) Header(destination)

                content(it)
            }
        },
    )
}

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = modifier,
        builder = builder
    )
}

fun NavHostController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) {
    navigate(destination.route, navOptions, navigatorExtras)
}

fun NavHostController.popBackStack(
    destination: Destination,
    inclusive: Boolean,
) = popBackStack(destination.route, inclusive)

fun NavController.getBackStackEntry(
    destination: Destination
) = getBackStackEntry(destination.route)

/**
 * [value] takes only primitives or [String] otherwise it won't work as expected.
 */
fun Destination.withParam(param: String, value: Any): PlantDetailsWithParam {
    return PlantDetailsWithParam(route.replace("{$param}", "{$value}"))
}

val UiNavigator = UiNavigationEventPropagator