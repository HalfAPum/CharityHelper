package com.narvatov.planthelper.di

import com.google.gson.GsonBuilder
import com.narvatov.planthelper.data.datasource.remote.api.FileApi
import com.narvatov.planthelper.data.datasource.remote.api.HelpApi
import com.narvatov.planthelper.data.datasource.remote.api.ProposalApi
import com.narvatov.planthelper.data.datasource.remote.api.SignApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
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
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    fun provideSignApi(retrofit: Retrofit) = retrofit.create(SignApi::class.java)

    single { provideSignApi(get()) }

    fun provideProposalApi(retrofit: Retrofit) = retrofit.create(ProposalApi::class.java)

    single { provideProposalApi(get()) }

    fun provideHelpApi(retrofit: Retrofit) = retrofit.create(HelpApi::class.java)

    single { provideHelpApi(get()) }

    fun provideFileApi(retrofit: Retrofit) = retrofit.create(FileApi::class.java)

    single { provideFileApi(get()) }

}

private const val BASE_URL = "http://10.0.2.2:8080/"