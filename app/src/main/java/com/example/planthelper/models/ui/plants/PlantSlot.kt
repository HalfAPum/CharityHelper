package com.example.planthelper.models.ui.plants

sealed interface PlantSlot {

    data class FilledSlot(val name: String) : PlantSlot

    object EmptySlot : PlantSlot

    object LockedSlot : PlantSlot

}