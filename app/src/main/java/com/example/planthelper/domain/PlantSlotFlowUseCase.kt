package com.example.planthelper.domain

import com.example.planthelper.data.repository.PlantRepository
import com.example.planthelper.data.repository.SettingsRepository
import com.example.planthelper.models.ui.plants.PlantSlot
import com.example.planthelper.models.ui.plants.PlantSlot.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import org.koin.core.annotation.Factory
import java.util.*

@Factory
class PlantSlotFlowUseCase(
    private val plantRepository: PlantRepository,
    private val settingsRepository: SettingsRepository,
) {

    operator fun invoke(): Flow<List<PlantSlot>> {
        return plantRepository.plantsFlow()
            .transform { plants ->
                val slots = LinkedList<PlantSlot>()

                val filledSlots = plants.map { FilledSlot(it) }
                val emptySlots = List(settingsRepository.getEmptySlotsCount()) { EmptySlot }
                val lockedSlots = List(settingsRepository.lockedSlots) { LockedSlot }

                slots.addAll(filledSlots)
                slots.addAll(emptySlots)
                slots.addAll(lockedSlots)

                emit(slots)
            }
    }
}