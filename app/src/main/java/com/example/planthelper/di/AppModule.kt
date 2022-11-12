package com.example.planthelper.di

import com.example.planthelper.data.repository.PlantRepository
import org.koin.dsl.module

val appModule = module {

    factory { PlantRepository(get()) }
}