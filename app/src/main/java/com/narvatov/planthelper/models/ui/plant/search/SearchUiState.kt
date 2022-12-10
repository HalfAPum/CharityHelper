package com.narvatov.planthelper.models.ui.plant.search

import androidx.compose.runtime.Immutable
import com.narvatov.planthelper.models.data.local.Plant

@Immutable
data class SearchUiState(
    val plants: List<Plant>
)

fun EmptySearchUiState() = SearchUiState(plants = emptyList())
