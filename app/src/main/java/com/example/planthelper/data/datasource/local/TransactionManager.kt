package com.example.planthelper.data.datasource.local

interface TransactionManager {

    suspend fun <R> withTransaction(block: suspend () -> R)
}