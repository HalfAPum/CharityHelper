package com.example.planthelper.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.planthelper.models.ui.feed.EmptyFeedUiState
import com.example.planthelper.models.ui.feed.FeedElement
import com.example.planthelper.models.ui.feed.FeedElement.FeedItem
import com.example.planthelper.models.ui.feed.FeedUiState
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class FeedViewModel : ViewModel() {

    var feedUiState by mutableStateOf(EmptyFeedUiState())
        private set

    init {
        loadFeeds()
    }

    private fun loadFeeds() {
        feedUiState = feedUiState.copy(feedItems = testData)
    }

    fun completeFeed(feedItem: FeedItem) {

    }

    fun declineFeed(feedItem: FeedItem) {

    }

}

private val testData = listOf(
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
    FeedItem("af"),
    FeedElement.FeedHeader("afkj"),
)