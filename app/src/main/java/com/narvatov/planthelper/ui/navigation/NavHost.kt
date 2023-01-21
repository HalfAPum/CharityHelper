package com.narvatov.planthelper.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.halfapum.general.coroutines.launchCatching
import com.narvatov.planthelper.models.ui.purchase.PurchaseDialog
import com.narvatov.planthelper.ui.screen.plant.create.Calendar
import com.narvatov.planthelper.ui.screen.plant.create.CreatePlant
import com.narvatov.planthelper.ui.screen.plant.create.search.SearchPlantType
import com.narvatov.planthelper.ui.screen.plant.details.PlantDetails
import com.narvatov.planthelper.ui.screen.plant.list.PlantsScreen
import com.narvatov.planthelper.ui.screen.purchase.CurrentPurchase
import com.narvatov.planthelper.ui.screen.purchase.PurchaseScreen
import com.narvatov.planthelper.ui.screen.settings.SettingsScreen
import com.narvatov.planthelper.ui.screen.task.TasksScreen
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavHostContent(
    navController: NavHostController,
    innerPadding: PaddingValues
) = with(navController) {
    val scope = rememberCoroutineScope()

    NavHost(
        navController = navController,
        startDestination = BottomNavigation.Tasks,
        modifier = Modifier.padding(innerPadding),
    ) {
        scope.launchCatching {
            UiNavigator.navigationEvents.collectLatest { destination ->
                when(destination) {
                    is BackWithParam -> {
                        val poppedBackSuccessfully = popBackStack(
                            destination = destination.back,
                            inclusive = destination.inclusive,
                        )

                        if (poppedBackSuccessfully) return@collectLatest

                        popBackStack(
                            destination = BottomNavigation.Tasks,
                            inclusive = false,
                        )
                    }
                    Back -> popBackStack()
                    else -> {
                        val poppedSuccessfully = popBackStack(
                            destination = destination,
                            inclusive = false,
                        )

                        if (poppedSuccessfully) return@collectLatest

                        navigate(destination)
                    }
                }

            }
        }

        bottomNavigation {
            composable(BottomNavigation.Tasks) {
                Column(
                    modifier = Modifier
                        .background(color = LightGreyBackground)
                        .padding(top = 8.dp)
                ) {
                    TasksScreen()
                }
            }

            composable(BottomNavigation.Plants) {
                PlantsScreen()
            }

            composable(BottomNavigation.Settings) {
                SettingsScreen()
            }
        }


        composable(PlantDetails) { navBackStackEntry ->
//            val plantId = navBackStackEntry.arguments?.getString(PlantDetails.PLANT_ID_NAV_PARAM)?.toLong()

//            plantId?.let {
                PlantDetails(plantId = navigationPlantId)
//            }
        }

        composable(CreatePlant) {
            CreatePlant(plantId = navigationEditPlantId)
        }

        composable(SearchPlantType) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(CreatePlant)
            }

            SearchPlantType(viewModel = getViewModel(
                owner = viewModelStateOwner,
                parameters = { parametersOf(navigationEditPlantId) }
            ))
        }

        composable(Calendar) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(CreatePlant)
            }

            Calendar(viewModel = getViewModel(
                owner = viewModelStateOwner,
                parameters = { parametersOf(navigationEditPlantId) }
            ))
        }

        composable(Purchase) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntryNullable(CurrentPurchase)
            }

            PurchaseScreen(viewModel = viewModelStateOwner?.let {
                    getViewModel(owner = it)
                } ?: getViewModel()
            )
        }

        composable(CurrentPurchase) {
            CurrentPurchase()
        }

        dialog(PurchaseDialog) {
            PurchaseDialog()
        }
    }
}

//TODO REALLY TEMP FIX NAVIGATION SHIT LATER
var navigationPlantId = 0L
var navigationEditPlantId: Long? = null