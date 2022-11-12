package com.example.planthelper.models.data.local

import androidx.annotation.FloatRange
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Plant(
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @FloatRange(from = 0.0, to = 100.0)
    @ColumnInfo(name = "age")
    val age: Double = 0.0,
    @FloatRange(from = 0.0, to = 1.0)
    @ColumnInfo(name = "health_percentage")
    val healthPercentage: Double = 1.0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1
)
