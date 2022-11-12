package com.example.planthelper.models.data.local.schedule

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @ColumnInfo(name = "plant_id")
    val plantId: Int,
    @ColumnInfo(name = "schedule_type")
    val scheduleType: ScheduleType,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded
    val monthSchedule: MonthSchedule,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = -1
)
