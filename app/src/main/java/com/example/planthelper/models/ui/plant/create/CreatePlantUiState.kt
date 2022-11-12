package com.example.planthelper.models.ui.plant.create

import com.example.planthelper.utils.isNotBlank
import com.example.planthelper.utils.shortBirthDay
import java.util.*

data class CreatePlantUiState(
    val plantName: String = "",
    val plantType: String = "",
    val imageUrl: String? = null,
    val defaultImageUrl: String = "",
    val plantBirthDay: Date = Date(),

    val isPlantNameError: Boolean = false,
    val isPlantTypeError: Boolean = false,
) {

    val shortPlantBirthDay: String
        get() = plantBirthDay.shortBirthDay

    val isValid: Boolean
        get() = plantName.isNotBlank() && plantType.isNotBlank()

    fun copyWithErrors() = copy(
        isPlantNameError = plantName.isNotBlank(),
        isPlantTypeError = plantType.isNotBlank(),
    )

}

fun EmptyCreatePlantUiState() = CreatePlantUiState()
