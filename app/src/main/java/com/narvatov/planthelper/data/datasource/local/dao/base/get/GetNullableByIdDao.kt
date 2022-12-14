package com.narvatov.planthelper.data.datasource.local.dao.base.get

interface GetNullableByIdDao<T> {

    @JvmSuppressWildcards
    suspend fun get(id: Long): T?

}