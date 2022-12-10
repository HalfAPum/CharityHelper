package com.narvatov.planthelper.models.ui.plants

import com.narvatov.planthelper.models.data.local.Plant

sealed interface PlantSlot {

    data class FilledSlot(val plant: Plant) : PlantSlot

    object EmptySlot : PlantSlot

    object LockedSlot : PlantSlot

}