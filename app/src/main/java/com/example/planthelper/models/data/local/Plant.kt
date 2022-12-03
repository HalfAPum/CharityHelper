package com.example.planthelper.models.data.local

import androidx.annotation.FloatRange
import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.ChronoUnit
import java.util.*

@Immutable
@Entity
data class Plant(
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "origin_name")
    val originName: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    @ColumnInfo(name = "birthday_date")
    val birthdayDate: Date = Date(),
    @FloatRange(from = 0.0, to = 1.0)
    @ColumnInfo(name = "health_percentage")
    val health: Double = 1.0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0
) {

    val plantName: String
        get() = name ?: (originName + id)

    //TODO refactor. I'm too lazy for that. Before refactoring cover it with tests
    val ageString: String
        get() {
            val sdf = SimpleDateFormat("YYYY-MM-dd")
            val formattedBirthday = sdf.format(birthdayDate)

            var monthDifference = ChronoUnit.MONTHS.between(
                YearMonth.from(LocalDate.parse(formattedBirthday)),
                YearMonth.from(LocalDate.now())
            ).toInt()

            return when (monthDifference) {
                0 -> "Just born"
                1 -> "1 month"
                in 0..11 -> "$monthDifference months"
                else -> {
                    var years = 0
                    while (monthDifference >= 12) {
                        monthDifference -= 12
                        years++
                    }
                    when (monthDifference) {
                        0 -> years.withMonths()
                        1 -> years.withMonths("1 month")
                        else -> years.withMonths("$monthDifference months")
                    }
                }
            }
        }

    private fun Int.withMonths(months: String = ""): String {
        return if (this == 1) "1 year $months"
        else "$this years $months"
    }

    val healthPercents: String
        get() = "${health * 100}%"

}
