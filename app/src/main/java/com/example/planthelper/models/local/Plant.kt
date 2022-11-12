package com.example.planthelper.models.local

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity
data class Plant(
    @PrimaryKey
    val id: String
)
