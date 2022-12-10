package com.narvatov.planthelper.data.datasource.local.dao.base

import androidx.room.Delete

interface DeleteDao<T> {

    @Delete
    suspend fun delete(item: T)

    @Delete
    suspend fun delete(items: List<T>)

}