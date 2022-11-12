package com.example.planthelper.data.datasource.local.helper

import com.example.planthelper.data.datasource.local.dao.PlantDao
import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.datasource.local.helper.base.DaoHelper
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import org.koin.core.annotation.Factory

@Factory
class SavePlantWithScheduleDaoHelper(
    private val plantDao: PlantDao,
    private val scheduleDao: ScheduleDao,
): DaoHelper() {

    suspend fun save(plant: Plant, schedules: List<Schedule>) = withTransaction {
        val plantId = plantDao.insert(plant)

        val schedulesWithPlantId = schedules.map { it.copy(plantId = plantId) }
        scheduleDao.insert(schedulesWithPlantId)
    }

}