package com.narvatov.planthelper.models.ui.plant.create

import androidx.compose.runtime.Immutable
import com.narvatov.planthelper.models.data.local.Plant
import com.narvatov.planthelper.utils.isNotBlank
import com.narvatov.planthelper.utils.shortBirthDay
import java.util.*

@Immutable
data class CreatePlantUiState(
    val plantName: String = "",
    val plantType: String = "",
    val imageUrl: String? = null,
    val defaultImageUrl: String = "",
    val plantBirthDay: Date = Date(),

    val isPlantNameError: Boolean = false,
    val isPlantTypeError: Boolean = false,

    val isCreateMode: Boolean = true
) {

    val shortPlantBirthDay: String
        get() = plantBirthDay.shortBirthDay

    val isValid: Boolean
        get() = plantName.isNotBlank() && plantType.isNotBlank()

    fun copyWithErrors() = copy(
        isPlantNameError = plantName.isBlank(),
        isPlantTypeError = plantType.isBlank(),
    )

    fun transformToPlant() = Plant(
        name = plantName,
        originName = plantType,
        imageUrl = imageUrl ?: defaultImageUrl,
        birthdayDate = plantBirthDay
    )

}

fun EmptyCreatePlantUiState() = CreatePlantUiState()
