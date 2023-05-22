package com.narvatov.planthelper.ui.screen

import androidx.compose.runtime.Composable
import com.narvatov.planthelper.ui.navigation.BottomNavigation
import com.narvatov.planthelper.ui.navigation.HelpDetails
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate

@Composable
fun NavigationStartStub() {

    if (isFirstLaunch) {
        navigate(BottomNavigation.Requests)
        isFirstLaunch = false
    }
}

private var isFirstLaunch = true