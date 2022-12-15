package com.narvatov.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.narvatov.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.narvatov.planthelper.data.datasource.local.dao.base.flow.FlowAllDao
import com.narvatov.planthelper.data.datasource.local.dao.base.flow.FlowByIdDao
import com.narvatov.planthelper.data.datasource.local.dao.base.get.GetNullableByIdDao
import com.narvatov.planthelper.models.data.local.task.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao : BaseDao<Task>, FlowByIdDao<Task>, FlowAllDao<Task>, GetNullableByIdDao<Task> {

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE id = :id")
    override fun flow(id: Long): Flow<Task>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE Task.plant_id = :id")
    fun flowByPlantId(id: Long): Flow<List<Task>>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task")
    override fun flowAll(): Flow<List<Task>>

    @JvmSuppressWildcards
    @Query("SELECT * FROM Task WHERE id = :id")
    override suspend fun get(id: Long): Task?


    @JvmSuppressWildcards
    @Query("""
        UPDATE Task 
        SET is_notification_shown = 1
        WHERE id = :id
    """)
    suspend fun markTaskNotificationShown(id: Long)

    @JvmSuppressWildcards
    @Query("""
        SELECT * FROM Task
        WHERE plant_id = :plantId AND schedule_id = :scheduleId AND is_notification_shown = 0
    """)
    suspend fun getNextNotificationTasks(plantId: Long, scheduleId: Long): List<Task>

}