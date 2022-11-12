package com.example.planthelper.models.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1
)
