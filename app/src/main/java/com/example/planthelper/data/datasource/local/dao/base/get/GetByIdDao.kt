package com.example.planthelper.data.datasource.local.dao.base.get

interface GetByIdDao<T> {

    @JvmSuppressWildcards
    suspend fun get(id: Long): T

}