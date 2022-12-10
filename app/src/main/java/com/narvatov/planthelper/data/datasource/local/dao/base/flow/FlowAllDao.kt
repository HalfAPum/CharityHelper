package com.narvatov.planthelper.data.datasource.local.dao.base.flow

import kotlinx.coroutines.flow.Flow

interface FlowAllDao<T> {

    @JvmSuppressWildcards
    fun flowAll(): Flow<List<T>>
}