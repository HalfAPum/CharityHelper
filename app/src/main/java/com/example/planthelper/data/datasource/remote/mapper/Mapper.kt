package com.example.planthelper.data.datasource.remote.mapper

import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.models.data.local.schedule.Schedule
import com.example.planthelper.models.data.local.schedule.ScheduleType
import com.example.planthelper.models.data.remote.PlantsResponse
import com.example.planthelper.models.data.remote.Scheduling

fun PlantsResponse.map(): Map<Plant, List<Schedule>> {
    val result = HashMap<Plant, List<Schedule>>(plants.size)

    plants.forEach { plantInfo ->
        val plant = Plant(
            originName = plantInfo.name,
            imageUrl = plantInfo.imageUrl,
            age = 0,
        )

        val waterSchedule = plantInfo.watering.map(ScheduleType.Watering)
        val fertilizerSchedule = plantInfo.fertilizer.map(ScheduleType.Fertilizer)
        val pruningSchedule = plantInfo.pruning.map(ScheduleType.Pruning)

        result[plant] = listOf(waterSchedule, fertilizerSchedule, pruningSchedule)
    }

    return result
}

fun Scheduling.map(scheduleType: ScheduleType) = Schedule(
    plantId = -1,
    scheduleType = scheduleType,
    name = name,
    monthSchedule = monthSchedule
)