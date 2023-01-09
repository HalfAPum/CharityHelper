package com.narvatov.planthelper.data.repository

import androidx.lifecycle.viewModelScope
import com.android.billingclient.api.Purchase
import com.narvatov.planthelper.data.repository.base.Repository
import com.narvatov.planthelper.data.repository.plant.PlantRepository
import com.narvatov.planthelper.data.utils.subscriptionIdsToSlotsMap
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.annotation.Factory

@Factory
class SettingsRepository(
    private val plantRepository: PlantRepository,
    private val billingRepository: BillingRepository,
): Repository() {

    var availableSlots: Int = DEFAULT_AVAILABLE_SLOT_COUNT
        private set

    init { collectPurchasedProductsFlow() }

    private fun collectPurchasedProductsFlow() {
        billingRepository.purchasedProductsFlow
            .onEach(::handlePurchases)
            .launchIn(repositoryScope)
    }

    private fun handlePurchases(purchasedList: List<Purchase>) {
        if (purchasedList.isEmpty()) return

        val lastProductId = purchasedList.last().products.first()

        availableSlots = subscriptionIdsToSlotsMap.getOrDefault(lastProductId, DEFAULT_AVAILABLE_SLOT_COUNT)
    }

    private suspend fun getOccupiedSlotsCount() = plantRepository.getAllPlants().size


    suspend fun getEmptySlotsCount() = when (availableSlots) {
        Int.MAX_VALUE -> {
            getOccupiedSlotsCount() + DEFAULT_AVAILABLE_SLOT_COUNT
        }
        else -> {
            val emptySlots = availableSlots - getOccupiedSlotsCount()
            emptySlots.coerceAtLeast(0)
        }
    }

    fun getLockedSlotsCount() = when (availableSlots) {
        Int.MAX_VALUE -> NO_SLOTS
        else -> DEFAULT_LOCKED_SLOTS_AMOUNT
    }

    companion object {
        private const val NO_SLOTS = 0
        private const val DEFAULT_AVAILABLE_SLOT_COUNT = 2
        private const val DEFAULT_LOCKED_SLOTS_AMOUNT = 6
    }
}