package com.narvatov.planthelper.models.data.local.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


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
    val scheduledDate: Date,
    @ColumnInfo(name = "status")
    val status: TaskStatus = TaskStatus.Scheduled,
    @ColumnInfo(name = "is_notification_shown")
    val isNotificationShown: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
) {

    val scheduledDateString: String
        get() {
            val sdf = SimpleDateFormat("MMM, dd")
            return sdf.format(scheduledDate)
        }

}