package com.example.planthelper.models.ui.plant.create

import androidx.compose.runtime.Immutable
import com.example.planthelper.models.data.local.Plant
import com.example.planthelper.utils.isNotBlank
import com.example.planthelper.utils.shortBirthDay
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
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
) {

    val shortPlantBirthDay: String
        get() = plantBirthDay.shortBirthDay

    val isValid: Boolean
        get() = plantName.isNotBlank() && plantType.isNotBlank()

    fun copyWithErrors() = copy(
        isPlantNameError = plantName.isBlank(),
        isPlantTypeError = plantType.isBlank(),
    )

    fun transformToPlant(): Plant {
        val sdf = SimpleDateFormat("YYYY-MM-dd")
        val formattedBirthday = sdf.format(plantBirthDay)

        return Plant(
            name = plantName,
            originName = plantType,
            imageUrl = imageUrl ?: defaultImageUrl,
            age = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(formattedBirthday)),
                YearMonth.from(LocalDate.now())
            ).toInt()
        )
    }

}

fun EmptyCreatePlantUiState() = CreatePlantUiState()
