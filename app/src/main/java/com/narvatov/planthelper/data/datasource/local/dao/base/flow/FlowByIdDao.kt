package com.narvatov.planthelper.data.datasource.local.dao.base.flow

import kotlinx.coroutines.flow.Flow

interface FlowByIdDao<T> {

    @JvmSuppressWildcards
    fun flow(id: Long): Flow<T?>

}