package com.example.planthelper.di

import com.example.planthelper.ui.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { FeedViewModel() }

    viewModel { PlantViewModel(get()) }

    viewModel { SettingsViewModel() }

    viewModel { PlantDetailsViewModel() }

    viewModel { CreatePlantViewModel() }

    viewModel { PurchaseViewModel() }

}