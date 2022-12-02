package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.Plant
import org.koin.core.annotation.Factory

@Factory
class ScheduleRepository(
    private val scheduleDao: ScheduleDao,
    private val plantInfoRepository: PlantInfoRepository,
) : Repository() {

    suspend fun addSchedulesForPlant(plant: Plant) {
        val schedules = plantInfoRepository.getPlantSchedules(plant)

        if (schedulesForPlantAlreadyExist(plant)) return

        scheduleDao.insert(schedules)
    }

    private suspend fun schedulesForPlantAlreadyExist(plant: Plant): Boolean = IOOperation {
        scheduleDao.getSchedulesByPlantOriginName(plant.originName).isNotEmpty()
    }

    suspend fun getSchedule(scheduleId: Long) = IOOperation { scheduleDao.get(scheduleId) }

}