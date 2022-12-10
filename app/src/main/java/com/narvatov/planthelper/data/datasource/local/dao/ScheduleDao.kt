package com.narvatov.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.narvatov.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.narvatov.planthelper.data.datasource.local.dao.base.get.GetAllDao
import com.narvatov.planthelper.data.datasource.local.dao.base.get.GetByIdDao
import com.narvatov.planthelper.models.data.local.schedule.Schedule

@Dao
interface ScheduleDao: BaseDao<Schedule>, GetByIdDao<Schedule>, GetAllDao<Schedule> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Schedule WHERE id = :id")
    override suspend fun get(id: Long): Schedule

    @JvmSuppressWildcards
    @Query("SELECT * FROM Schedule")
    override suspend fun getAll(): List<Schedule>

    @Query("SELECT * FROM Schedule WHERE origin_plant_name = :plantOriginName")
    suspend fun getSchedulesByPlantOriginName(plantOriginName: String): List<Schedule>

}