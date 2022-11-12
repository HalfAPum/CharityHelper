package com.example.planthelper.data.datasource.remote.api

import com.example.planthelper.di.PLANT_INFO_JSON_URL
import com.example.planthelper.models.data.remote.PlantsResponse
import retrofit2.http.GET

interface PlantApi {

    @GET(PLANT_INFO_JSON_URL)
    suspend fun getPlants(): PlantsResponse

}