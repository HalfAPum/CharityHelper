package com.example.planthelper.di

import com.example.planthelper.ui.viewmodel.PlantDetailsViewModel
import com.example.planthelper.ui.viewmodel.TaskViewModel
import com.halfapum.general.coroutines.Dispatcher
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Dispatcher() }

    viewModel { params -> PlantDetailsViewModel(params.get()) }

    viewModel { params -> TaskViewModel(params.getOrNull(), get(), get(), get()) }

}