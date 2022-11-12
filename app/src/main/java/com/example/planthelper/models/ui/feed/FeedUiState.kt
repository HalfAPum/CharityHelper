package com.example.planthelper.models.ui.feed

import androidx.compose.runtime.Immutable

@Immutable
data class FeedUiState(
    val feedItems: List<FeedElement>
)

fun EmptyFeedUiState() = FeedUiState(emptyList())
