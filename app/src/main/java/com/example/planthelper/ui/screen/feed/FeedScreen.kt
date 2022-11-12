package com.example.planthelper.ui.screen.feed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.planthelper.models.ui.feed.FeedElement.FeedHeader
import com.example.planthelper.models.ui.feed.FeedElement.FeedItem
import com.example.planthelper.ui.viewmodel.FeedViewModel
import com.example.planthelper.utils.GenericCallback
import com.example.planthelper.utils.UnitCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun FeedScreen(
    onFeedClicked: GenericCallback<FeedItem>,
    viewModel: FeedViewModel = getViewModel()
) {
    LazyColumn {
        items(viewModel.feedUiState.feedItems) { item ->
            when (item) {
                is FeedItem -> FeedItem(
                    feedItem = item,
                    onFeedClicked = onFeedClicked,
                    onComplete = { viewModel.completeFeed(it) },
                    onDecline = { viewModel.declineFeed(it) }
                )
                is FeedHeader -> FeedHeader(item)
            }
        }
    }
}

@Composable
fun FeedItem(
    feedItem: FeedItem,
    onFeedClicked: GenericCallback<FeedItem>,
    onComplete: GenericCallback<FeedItem>,
    onDecline: GenericCallback<FeedItem>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.clickable { onFeedClicked(feedItem) }) {
        Text(feedItem.toString())
    }
}

@Composable
fun FeedHeader(
    feedHeader: FeedHeader,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(feedHeader.toString())
    }
}



@Preview(showBackground = true)
@Composable
fun FeedScreenPreview() {
    FeedScreen({})
}