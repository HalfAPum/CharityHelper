package com.example.planthelper.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.planthelper.ui.navigation.Destination.*
import com.example.planthelper.ui.screen.feed.FeedScreen
import com.example.planthelper.ui.screen.plant.create.Calendar
import com.example.planthelper.ui.screen.plant.create.CreatePlant
import com.example.planthelper.ui.screen.plant.create.search.SearchPlantType
import com.example.planthelper.ui.screen.plant.details.PlantDetails
import com.example.planthelper.ui.screen.plant.list.PlantsScreen
import com.example.planthelper.ui.screen.purchase.Purchase
import com.example.planthelper.ui.screen.settings.SettingsScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun NavHostContent(
    navController: NavHostController,
    innerPadding: PaddingValues
) = with(navController) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigation.Feed,
        modifier = Modifier.padding(innerPadding),
    ) {

        bottomNavigation {
            composable(BottomNavigation.Feed) {
                FeedScreen(onFeedClicked = {
                    navigate(PlantDetails)
                })
            }

            composable(BottomNavigation.Plants) {
                PlantsScreen(
                    onPlantClicked = {
                        navigate(PlantDetails)
                    },
                    onEmptySlotClicked = {
                        navigate(CreatePlant)
                    },
                    onLockedSlotClicked = {
                        navigate(Purchase)
                    }
                )
            }

            composable(BottomNavigation.Settings) {
                SettingsScreen(
                    onPurchaseClicked = {
                        navigate(Purchase)
                    },
                )
            }
        }

        composable(PlantDetails) {
            PlantDetails(onPlantDeleteClicked = {
                popBackStack()
            })
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
                    popBackStack()
                },
            )
        }

        composable(SearchPlantType) {
            SearchPlantType()
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

