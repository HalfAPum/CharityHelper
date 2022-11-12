package com.example.planthelper.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.planthelper.ui.navigation.Destination.*
import com.example.planthelper.ui.navigation.Destination.BottomNavigation
import com.example.planthelper.ui.screen.feed.FeedScreen
import com.example.planthelper.ui.screen.plant.create.CreatePlant
import com.example.planthelper.ui.screen.plant.details.PlantDetails
import com.example.planthelper.ui.screen.plants.PlantsScreen
import com.example.planthelper.ui.screen.purchase.Purchase
import com.example.planthelper.ui.screen.settings.SettingsScreen

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
                SettingsScreen(onPurchaseClicked = {
                    navigate(Purchase)
                })
            }
        }

        composable(PlantDetails) {
            PlantDetails(onPlantDeleteClicked = {
                popBackStack()
            })
        }

        composable(CreatePlant) {
            CreatePlant(onPlantCreated = {
                popBackStack()
            })
        }

        composable(Purchase) {
            Purchase()
        }

    }
}

