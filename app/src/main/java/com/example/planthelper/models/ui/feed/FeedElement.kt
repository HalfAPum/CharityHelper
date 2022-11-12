package com.example.planthelper.models.ui.feed

import androidx.compose.runtime.Immutable

sealed interface FeedElement {

    @Immutable
    data class FeedHeader(val date: String) : FeedElement

    @Immutable
    data class FeedItem(val name: String) : FeedElement

}
