package com.narvatov.planthelper.data.datasource.remote.helper

import com.narvatov.planthelper.data.datasource.remote.api.PlantApi
import com.narvatov.planthelper.data.datasource.remote.mapper.map
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.models.data.local.schedule.Schedule
import org.koin.core.annotation.Factory

@Factory
class PlantInfoApiHelper(private val api: PlantApi) {

    suspend fun getPlants() : Map<Plant, List<Schedule>> {
        val response = api.getPlants()
        return response.map()
    }

}