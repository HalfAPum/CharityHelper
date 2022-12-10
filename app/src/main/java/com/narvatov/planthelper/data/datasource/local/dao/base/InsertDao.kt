package com.narvatov.planthelper.data.datasource.local.dao.base

import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface InsertDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(items: List<T>)

}