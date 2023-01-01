package com.narvatov.planthelper.domain.plant

import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.data.repository.SettingsRepository
import com.narvatov.planthelper.models.ui.plants.slot.EmptyPlantSlot
import com.narvatov.planthelper.models.ui.plants.slot.FilledPlantSlot
import com.narvatov.planthelper.models.ui.plants.slot.LockedPlantSlot
import com.narvatov.planthelper.models.ui.plants.slot.PlantSlot
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

                val filledSlots = plants.map { FilledPlantSlot(it) }
                val emptySlots = List(settingsRepository.getEmptySlotsCount()) { EmptyPlantSlot }
                val lockedSlots = List(settingsRepository.lockedSlots) { LockedPlantSlot }

                slots.addAll(filledSlots)
                slots.addAll(emptySlots)
                slots.addAll(lockedSlots)

                emit(slots)
            }
    }
}