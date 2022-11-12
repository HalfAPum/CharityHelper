package com.example.planthelper.models.data.local.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @ColumnInfo(name = "schedule_id")
    val scheduleId: Int,
    @ColumnInfo(name = "status")
    val status: TaskStatus,
    @ColumnInfo(name = "task_date")
    val taskDate: String,
    @ColumnInfo(name = "finish_date")
    val finishDate: String,
    @ColumnInfo(name = "name")
    val name: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = -1,
)