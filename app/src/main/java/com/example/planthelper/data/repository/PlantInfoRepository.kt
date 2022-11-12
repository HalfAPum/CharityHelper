package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.utils.Dispatcher
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class PlantInfoRepository(
    private val plantHelper: PlantInfoApiHelper,
) : Repository() {

    private val allPlantsInfo: HashMap<Plant, List<Schedule>> = HashMap()

    suspend fun getAllPlants(): Map<Plant, List<Schedule>> = withContext(IODispatcher) {
        if (allPlantsInfo.isEmpty()) {
            allPlantsInfo.putAll(plantHelper.getPlants())
        }

        return@withContext allPlantsInfo
    }

    suspend fun getPlantSchedules(
        plant: Plant
    ) = getAllPlants().getSchedules(plant)

    private fun Map<Plant, List<Schedule>>.getSchedules(plant: Plant): List<Schedule> {
        val plantKey = keys.firstOrNull { it.originName == plant.originName }
        return get(plantKey) ?: emptyList()
    }

}