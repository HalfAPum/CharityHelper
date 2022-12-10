package com.narvatov.planthelper.data.datasource.local

interface TransactionManager {

    suspend fun <R> withTransaction(block: suspend () -> R): R

}