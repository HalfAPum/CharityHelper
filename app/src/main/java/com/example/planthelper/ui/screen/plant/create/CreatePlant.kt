package com.example.planthelper.ui.screen.plant.create

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.planthelper.ui.viewmodel.CreatePlantViewModel
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun CreatePlant(
    onPlantCreated: UnitCallback,
    viewModel: CreatePlantViewModel = getViewModel()
) {
    Text("CreatePlantScreen")
}