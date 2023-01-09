package com.narvatov.planthelper.models.data.local

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity
data class BillingSubscription(
    @PrimaryKey
    @ColumnInfo(name = "product_id")
    val productId: String,
)