package com.example.planthelper.ui.screen.plant.details

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.planthelper.ui.viewmodel.PlantDetailsViewModel
import com.example.planthelper.ui.viewmodel.PurchaseViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun PlantDetails(
    onPlantDeleteClicked: UnitCallback,
    viewModel: PlantDetailsViewModel = getViewModel()
) {
    Text("PLANTDETAILS")
}