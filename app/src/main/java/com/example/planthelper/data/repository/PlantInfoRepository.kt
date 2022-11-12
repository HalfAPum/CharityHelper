package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import org.koin.core.annotation.Single

@Single
class PlantInfoRepository(
    private val plantHelper: PlantInfoApiHelper,
) : Repository() {

    private val allPlantsInfo: HashMap<Plant, List<Schedule>> = HashMap()

    suspend fun getAllPlants(): Map<Plant, List<Schedule>> = IOOperation {
        if (allPlantsInfo.isEmpty()) {
            allPlantsInfo.putAll(plantHelper.getPlants())
        }

        return@IOOperation allPlantsInfo
    }

    suspend fun getPlantSchedules(
        plant: Plant
    ) = getAllPlants().getSchedules(plant)

    private fun Map<Plant, List<Schedule>>.getSchedules(plant: Plant): List<Schedule> {
        val plantKey = keys.firstOrNull { it.originName == plant.originName }
        return get(plantKey) ?: emptyList()
    }

    suspend fun searchPlant(query: String) = IOOperation {
        getAllPlants().search(query)
    }

    private fun Map<Plant, *>.search(
        query: String,
    ) = keys.filter {
        if (query == SEARCH_ALL_PLANTS_QUERY) true
        else it.originName.contains(query, ignoreCase = true)
    }

    suspend fun loadPlantTypes() { searchPlant(SEARCH_ALL_PLANTS_QUERY) }

    companion object {
        internal const val SEARCH_ALL_PLANTS_QUERY = ""
    }

}