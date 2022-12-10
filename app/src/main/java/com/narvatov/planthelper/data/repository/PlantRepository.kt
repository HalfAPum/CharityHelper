package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.datasource.local.dao.PlantDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.utils.previewPlant
import org.koin.core.annotation.Factory

@Factory
class PlantRepository(
    private val plantDao: PlantDao,
) : Repository() {

    suspend fun addPlant(plant: Plant): Long = IOOperation {
        plantDao.insert(plant)
    }

    suspend fun updatePlant(plant: Plant) = IOOperation {
        plantDao.update(plant)
    }

    suspend fun deletePlant(plant: Plant) = IOOperation {
        plantDao.delete(plant)
    }

    suspend fun getPlant(id: Long) = plantDao.get(id)

    fun plantsFlow() = plantDao.flowAll()

    fun plantFlow(plantId: Long) = plantDao.flow(plantId)

    private val testData = listOf(
        previewPlant,
        previewPlant,
        previewPlant,
    )
}