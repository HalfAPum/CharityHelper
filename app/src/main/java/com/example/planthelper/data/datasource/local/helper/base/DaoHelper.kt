package com.example.planthelper.data.datasource.local.helper.base

import com.example.planthelper.data.datasource.local.TransactionManager
import org.koin.androidx.compose.inject

open class DaoHelper {

    private val transactionManager: TransactionManager by inject()

    protected suspend fun <R> withTransaction(block: suspend () -> R) {
        transactionManager.withTransaction(block)
    }

}