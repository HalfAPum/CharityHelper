package com.example.planthelper.models.data.local.schedule

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.example.planthelper.models.data.local.Plant

@Entity(foreignKeys = [
    ForeignKey(
        entity = Plant::class,
        parentColumns = ["id"],
        childColumns = ["plant_id"],
        onDelete = CASCADE,
        onUpdate = CASCADE,
    )
])
data class Schedule(
    @ColumnInfo(name = "plant_id")
    val plantId: Long = -1,
    @ColumnInfo(name = "schedule_type")
    val scheduleType: ScheduleType,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "month_schedule")
    val monthSchedule: Map<String, Int>,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = -1
)
