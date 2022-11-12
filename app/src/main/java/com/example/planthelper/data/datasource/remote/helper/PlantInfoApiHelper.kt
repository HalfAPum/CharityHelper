package com.example.planthelper.data.datasource.remote.helper

import com.example.planthelper.data.datasource.remote.api.PlantApi
import timber.log.Timber

class PlantInfoApiHelper(private val api: PlantApi) {

    suspend fun getPlants() {
        val response = api.getPlants()
//        val mappedData = response.map()
    }
}