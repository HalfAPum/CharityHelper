package com.example.planthelper.data.datasource.remote.helper

import com.example.planthelper.data.datasource.remote.api.PlantApi
import com.example.planthelper.data.datasource.remote.mapper.map
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import org.koin.core.annotation.Factory
import timber.log.Timber

@Factory
class PlantInfoApiHelper(private val api: PlantApi) {

    suspend fun getPlants() : Map<Plant, List<Schedule>> {
        val response = api.getPlants()
        return response.map()
    }

}