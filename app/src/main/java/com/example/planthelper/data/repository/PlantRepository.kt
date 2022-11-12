package com.example.planthelper.data.repository

import androidx.compose.runtime.key
import com.example.planthelper.data.datasource.local.dao.PlantDao
import com.example.planthelper.data.datasource.local.helper.SavePlantWithScheduleDaoHelper
import com.example.planthelper.data.datasource.remote.helper.PlantInfoApiHelper
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.utils.Dispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Single

@Factory
class PlantRepository(
    private val plantInfoRepository: PlantInfoRepository,
    private val savePlantWithScheduleDaoHelper: SavePlantWithScheduleDaoHelper,
    private val plantDao: PlantDao,
) : Repository() {

    suspend fun addPlant(plant: Plant) = withContext(IODispatcher) {
        val schedules = plantInfoRepository.getPlantSchedules(plant)

        savePlantWithScheduleDaoHelper.save(plant, schedules)
    }

    suspend fun updatePlant(plant: Plant) = withContext(IODispatcher) {
        plantDao.update(plant)
    }

    suspend fun deletePlant(plant: Plant) = withContext(IODispatcher) {
        plantDao.delete(plant)
    }

    suspend fun searchPlant(query: String) = withContext(IODispatcher) {
        plantInfoRepository.getAllPlants().search(query)
    }

    private fun Map<Plant, *>.search(
        query: String,
    ) = keys.filter { it.originName.contains(query) }

}