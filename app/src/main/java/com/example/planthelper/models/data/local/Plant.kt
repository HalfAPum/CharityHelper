package com.example.planthelper.models.data.local

import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Immutable
@Entity
data class Plant(
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @IntRange(from = 0, to = 240)
    @ColumnInfo(name = "age_month")
    val age: Int,
    @FloatRange(from = 0.0, to = 1.0)
    @ColumnInfo(name = "health_percentage")
    val health: Double = 1.0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1
) {

    val plantName: String
        get() = name ?: (originName + id)

    val ageString: String
        get() = "$age months"

    val healthPercents: String
        get() = "${health * 100}%"

}
