package com.example.planthelper.data.datasource.local.helper.base

import com.example.planthelper.data.datasource.local.TransactionManager
import com.example.planthelper.utils.inject

open class DaoHelper {

    private val transactionManager: TransactionManager by inject()

    protected suspend fun <R> withTransaction(block: suspend () -> R): R {
        return transactionManager.withTransaction(block)
    }

}