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
import com.narvatov.planthelper.ui.screen.plant.create.Calendar
import com.narvatov.planthelper.ui.screen.plant.create.CreatePlant
import com.narvatov.planthelper.ui.screen.plant.create.search.SearchPlantType
import com.narvatov.planthelper.ui.screen.plant.details.PlantDetails
import com.narvatov.planthelper.ui.screen.plant.list.PlantsScreen
import com.narvatov.planthelper.ui.screen.purchase.Purchase
import com.narvatov.planthelper.ui.screen.settings.SettingsScreen
import com.narvatov.planthelper.ui.screen.task.TasksScreen
import com.narvatov.planthelper.ui.theme.LightGreyBackground
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

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
                navigate(destination)
            }
        }

        bottomNavigation {
            composable(BottomNavigation.Tasks) {
                Column(
                    modifier = Modifier
                        .background(color = LightGreyBackground)
                        .padding(top = 8.dp)
                ) {
                    TasksScreen(
                        onTaskClicked = {
                            navigationPlantId = it.id
                            navigate(PlantDetails.withParam(PlantDetails.PLANT_ID_NAV_PARAM, it.id))
                        }
                    )
                }
            }

            composable(BottomNavigation.Plants) {
                PlantsScreen()
            }

            composable(BottomNavigation.Settings) {
                SettingsScreen(
                    onPurchaseClicked = {
                        navigate(Purchase)
                    },
                )
            }
        }


        composable(PlantDetails) { navBackStackEntry ->
//            val plantId = navBackStackEntry.arguments?.getString(PlantDetails.PLANT_ID_NAV_PARAM)?.toLong()

//            plantId?.let {
                PlantDetails(
                    plantId = navigationPlantId,
                    onDeleteClicked = {
                        popBackStack()
                    },
                    onEditClicked = {
                        //TODO GO TO EDIT PAGE
                    },
                )
//            }
        }

        composable(CreatePlant) {
            CreatePlant(
                onPlantTypeSearchClicked = {
                    navigate(SearchPlantType)
                },
                openCalendarClicked = {
                    navigate(Calendar)
                },
                onPlantSaved = {
                    popBackStack(BottomNavigation.Plants, inclusive = true)
                },
            )
        }

        composable(SearchPlantType) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(CreatePlant)
            }

            SearchPlantType(
                onPlantTypeSelected = { popBackStack() },
                viewModel = getViewModel(owner = viewModelStateOwner),
            )
        }

        composable(Calendar) {
            val viewModelStateOwner = remember {
                navController.getBackStackEntry(CreatePlant)
            }

            Calendar(
                onDismiss = { popBackStack() },
                viewModel = getViewModel(owner = viewModelStateOwner)
            )
        }

        composable(Purchase) {
            Purchase()
        }

    }
}

//TODO REALLY TEMP FIX NAVIGATION SHIT LATER
var navigationPlantId = 0L