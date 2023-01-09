package com.narvatov.planthelper.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.narvatov.planthelper.data.datasource.local.dao.base.combined.BaseDao
import com.narvatov.planthelper.models.data.local.BillingSubscription
import kotlinx.coroutines.flow.Flow

@Dao
interface BillingDao : BaseDao<BillingSubscription> {

    @Query("SELECT * FROM BillingSubscription")
    fun flowAll(): Flow<List<BillingSubscription>>

    @Query("DELETE FROM BillingSubscription")
    suspend fun clear()

}