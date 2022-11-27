package com.example.planthelper.data.repository

import com.example.planthelper.data.datasource.local.dao.ScheduleDao
import com.example.planthelper.data.repository.base.Repository
import com.example.planthelper.models.data.local.schedule.Schedule
import org.koin.core.annotation.Factory

@Factory
class ScheduleRepository(
    private val scheduleDao: ScheduleDao,
) : Repository() {

    suspend fun addSchedule(schedule: Schedule) = scheduleDao.insert(schedule)

    suspend fun getSchedule(scheduleId: Long) = scheduleDao.get(scheduleId)

}