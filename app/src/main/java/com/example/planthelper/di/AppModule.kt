package com.example.planthelper.di

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.utils.Dispatcher
import org.koin.dsl.module

val appModule = module {

    single { Dispatcher() }

}