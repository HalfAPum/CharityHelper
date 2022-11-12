package com.example.planthelper.data.datasource.local.dao.base.get

interface GetAllDao<T> {

    @JvmSuppressWildcards
    suspend fun getAll(): List<T>

}