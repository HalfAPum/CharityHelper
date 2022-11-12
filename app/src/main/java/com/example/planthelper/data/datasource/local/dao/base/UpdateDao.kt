package com.example.planthelper.data.datasource.local.dao.base

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface UpdateDao<T> {

    @Update
    suspend fun update(item: T)

    @Update
    suspend fun update(items: List<T>)

}