package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.local.dao.PlantDao
import com.example.planthelper.data.datasource.local.helper.SavePlantWithScheduleDaoHelper
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.utils.previewPlant
import kotlinx.coroutines.flow.flowOf
import org.koin.core.annotation.Factory

@Factory
class PlantRepository(
    private val plantInfoRepository: PlantInfoRepository,
    private val savePlantWithScheduleDaoHelper: SavePlantWithScheduleDaoHelper,
    private val plantDao: PlantDao,
) : Repository() {

    suspend fun addPlant(plant: Plant) = IOOperation {
        val schedules = plantInfoRepository.getPlantSchedules(plant)

        savePlantWithScheduleDaoHelper.save(plant, schedules)
    }

    suspend fun updatePlant(plant: Plant) = IOOperation {
        plantDao.update(plant)
    }

    suspend fun deletePlant(plant: Plant) = IOOperation {
        plantDao.delete(plant)
    }

    fun plantsFlow() =
        //TEMP
        flowOf(testData)
        // REAL
        // plantDao.flowAll()

    suspend fun searchPlant(query: String) = plantInfoRepository.searchPlant(query)


    private val testData = listOf(
        previewPlant,
        previewPlant,
        previewPlant,
    )
}