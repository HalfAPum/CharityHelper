package com.example.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.example.planthelper.data.datasource.local.dao.base.flow.FlowAllDao
import com.example.planthelper.data.datasource.local.dao.base.flow.FlowByIdDao
import com.example.planthelper.data.datasource.local.dao.base.get.GetByIdDao
import com.example.planthelper.models.data.local.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao : BaseDao<Plant>, FlowByIdDao<Plant>, FlowAllDao<Plant>, GetByIdDao<Plant> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Plant WHERE id = :id")
    override suspend fun get(id: Long): Plant

    @JvmSuppressWildcards
    @Query("SELECT * FROM Plant WHERE id = :id")
    override fun flow(id: Long): Flow<Plant>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Plant")
    override fun flowAll(): Flow<List<Plant>>

}