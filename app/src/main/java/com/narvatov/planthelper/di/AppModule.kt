package com.narvatov.planthelper.di

import com.halfapum.general.coroutines.Dispatcher
import com.narvatov.planthelper.ui.viewmodel.TaskViewModel
import com.narvatov.planthelper.ui.viewmodel.plant.details.PlantDetailsViewModel
import com.narvatov.planthelper.ui.worker.NotificationWorker
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val appModule = module {

    single { Dispatcher() }

    viewModel { params -> PlantDetailsViewModel(params.get(), get()) }

    viewModel { params -> TaskViewModel(params.getOrNull(), get(), get(), get()) }

    worker { NotificationWorker(get(), get(), androidApplication(), get()) }

}