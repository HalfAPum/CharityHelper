package com.example.planthelper.data.datasource.local.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface DeleteDao<T> {

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)

}