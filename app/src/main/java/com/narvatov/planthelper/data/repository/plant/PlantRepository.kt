package com.narvatov.planthelper.data.repository.plant

import com.narvatov.planthelper.data.datasource.local.dao.PlantDao
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.repository.task.TaskRepository
import com.narvatov.planthelper.models.data.local.Plant
import org.koin.core.annotation.Factory

@Factory
class PlantRepository(
    private val plantDao: PlantDao,
    private val taskRepository: TaskRepository,
) : Repository() {

    suspend fun addPlant(plant: Plant): Long = IOOperation {
        plantDao.insert(plant)
    }

    suspend fun updatePlant(plant: Plant) = IOOperation {
        plantDao.update(plant)
    }

    suspend fun deletePlant(plantId: Long) = IOOperation {
        plantDao.delete(plantId)

        taskRepository.deleteTasks(plantId)
    }

    suspend fun getPlant(id: Long) = plantDao.get(id)

    fun plantsFlow() = plantDao.flowAll()

    fun plantFlow(plantId: Long) = plantDao.flow(plantId)

    suspend fun getAllPlants() = plantDao.getAll()

}