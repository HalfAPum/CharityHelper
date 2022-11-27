package com.example.planthelper.models.data.local.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(foreignKeys = [
//    ForeignKey(
//        entity = Schedule::class,
//        parentColumns = ["id"],
//        childColumns = ["schedule_id"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE,
//    ),
//    ForeignKey(
//        entity = Plant::class,
//        parentColumns = ["id"],
//        childColumns = ["plant_id"],
//        onDelete = ForeignKey.CASCADE,
//        onUpdate = ForeignKey.CASCADE,
//    )
])
data class Task(
    @ColumnInfo(name = "plant_id")
    val plantId: Long,
    @ColumnInfo(name = "schedule_id")
    val scheduleId: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "health_impact")
    val healthImpact: Double,
    @ColumnInfo(name = "task_date")
    val scheduledDate: String,
    @ColumnInfo(name = "status")
    val status: TaskStatus = TaskStatus.Scheduled,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
)