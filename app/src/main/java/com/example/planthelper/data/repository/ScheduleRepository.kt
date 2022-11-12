package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.repository.base.Repository
import org.koin.core.annotation.Factory

@Factory
class ScheduleRepository(
    private val scheduleDao: ScheduleDao,
) : Repository() {

    suspend fun getSchedule(scheduleId: Long) = scheduleDao.get(scheduleId)

}