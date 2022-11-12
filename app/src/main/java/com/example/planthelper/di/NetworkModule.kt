package com.example.planthelper.di

import com.example.planthelper.data.datasource.remote.api.PlantApi
import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
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

    factory { PlantInfoApiHelper(get()) }

}

private const val PLANT_INFO_JSON_BASE_URL = "https://raw.githubusercontent.com/"
const val PLANT_INFO_JSON_URL = "https://raw.githubusercontent.com/HalfAPum/Planthelper/master/release/plant_info.json?token=GHSAT0AAAAAABYUMQU37ZHOU7EAJ2MIYC74YZQB6ZQ"