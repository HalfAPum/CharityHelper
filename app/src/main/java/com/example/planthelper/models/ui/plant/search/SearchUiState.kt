package com.example.planthelper.models.ui.plant.search

import androidx.compose.runtime.Immutable
import com.example.planthelper.models.data.local.Plant

@Immutable
data class SearchUiState(
    val plants: List<Plant>
)

fun EmptySearchUiState() = SearchUiState(plants = emptyList())
