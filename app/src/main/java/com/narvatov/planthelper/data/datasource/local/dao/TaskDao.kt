package com.narvatov.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.narvatov.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.narvatov.planthelper.data.datasource.local.dao.base.flow.FlowAllDao
import com.narvatov.planthelper.data.datasource.local.dao.base.flow.FlowByIdDao
import com.narvatov.planthelper.models.data.local.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<Task>, FlowByIdDao<Task>, FlowAllDao<Task> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE id = :id")
    override fun flow(id: Long): Flow<Task>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE Task.plant_id = :id")
    fun flowByPlantId(id: Long): Flow<List<Task>>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task")
    override fun flowAll(): Flow<List<Task>>

}