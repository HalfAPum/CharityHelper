package com.narvatov.planthelper.models.data.local.schedule

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule(
    @ColumnInfo(name = "origin_plant_name")
    val plantName: String,
    @ColumnInfo(name = "schedule_type")
    val scheduleType: ScheduleType,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "month_schedule")
    val monthSchedule: Map<String, Int>,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
) {

    val notificationChannelId
        get() = scheduleType.notificationChannel

}
