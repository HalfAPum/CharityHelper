package com.example.planthelper.data.datasource.remote.helper

import com.example.planthelper.data.datasource.remote.api.PlantApi
import com.example.planthelper.models.data.local.Plant
import org.koin.core.annotation.Factory
import timber.log.Timber

@Factory
class PlantInfoApiHelper(private val api: PlantApi) {

    suspend fun getPlants() : List<Plant> {
        val response = api.getPlants()
        return emptyList()
//        val mappedData = response.map()
    }
}