package com.narvatov.planthelper.di

import com.halfapum.general.coroutines.Dispatcher
import org.koin.dsl.module

val appModule = module {

    single { Dispatcher() }



}