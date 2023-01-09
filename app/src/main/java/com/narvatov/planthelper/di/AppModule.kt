package com.narvatov.planthelper.di

import com.android.billingclient.api.BillingClient
import com.halfapum.general.coroutines.Dispatcher
import com.narvatov.planthelper.data.utils.BillingPurchaseListener
import com.narvatov.planthelper.ui.viewmodel.TaskViewModel
import com.narvatov.planthelper.ui.viewmodel.plant.create.CreatePlantViewModel
import com.narvatov.planthelper.ui.viewmodel.plant.details.PlantDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Dispatcher() }


    viewModel { params -> PlantDetailsViewModel(params.get(), get(), get()) }

    viewModel { params -> CreatePlantViewModel(params.getOrNull(), get(), get(), get(), get(), get()) }

    viewModel { params -> TaskViewModel(params.getOrNull(), get(), get(), get()) }


    single {
        BillingClient.newBuilder(androidContext()).run {
            setListener(BillingPurchaseListener)
            enablePendingPurchases()
            build()
        }
    }

}