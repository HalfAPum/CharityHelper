package com.example.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.example.planthelper.data.datasource.local.dao.base.get.GetAllDao
import com.example.planthelper.data.datasource.local.dao.base.get.GetByIdDao
import com.example.planthelper.models.data.local.schedule.Schedule

@Dao
interface ScheduleDao: BaseDao<Schedule>, GetByIdDao<Schedule>, GetAllDao<Schedule> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Schedule WHERE id = :id")
    override suspend fun get(id: Int): Schedule

    @JvmSuppressWildcards
    @Query("SELECT * FROM Schedule")
    override suspend fun getAll(): List<Schedule>

}