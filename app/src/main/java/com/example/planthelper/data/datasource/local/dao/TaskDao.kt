package com.example.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.example.planthelper.data.datasource.local.dao.base.flow.FlowAllDao
import com.example.planthelper.data.datasource.local.dao.base.flow.FlowByIdDao
import com.example.planthelper.models.data.local.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<Task>, FlowByIdDao<Task>, FlowAllDao<Task> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE id = :id")
    override fun flow(id: Int): Flow<Task>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task")
    override fun flowAll(): Flow<List<Task>>

}