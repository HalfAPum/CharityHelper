package com.narvatov.planthelper.di

import com.narvatov.planthelper.data.datasource.remote.api.PlantApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    fun provideOkHttpClient(
        interceptor: HttpLoggingInterceptor
    ) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    single { provideOkHttpClient(get()) }

    single {
        Retrofit.Builder()
            .baseUrl(PLANT_INFO_JSON_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun providePlantInfoApi(retrofit: Retrofit) = retrofit.create(PlantApi::class.java)

    single { providePlantInfoApi(get()) }

}

private const val PLANT_INFO_JSON_BASE_URL = "https://drive.google.com/"
const val PLANT_INFO_JSON_URL = "https://drive.google.com/uc?export=download&confirm=no_antivirus&id=1LeIzmxhkMfyaaEfQOS_OcdDBFGuvSveT"