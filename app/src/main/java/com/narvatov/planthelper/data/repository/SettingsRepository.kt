package com.narvatov.planthelper.data.repository

import com.narvatov.planthelper.data.repository.plant.PlantRepository
import kotlinx.coroutines.flow.first
import org.koin.core.annotation.Factory

@Factory
class SettingsRepository(
    private val plantRepository: PlantRepository,
) {

    val lockedSlots: Int
        get() = LOCKED_SLOTS_AMOUNT

    suspend fun getEmptySlotsCount(): Int {
        //STUB
        return 5 - plantRepository.plantsFlow().first().size
    }

    companion object {
        private const val LOCKED_SLOTS_AMOUNT = 3
    }
}