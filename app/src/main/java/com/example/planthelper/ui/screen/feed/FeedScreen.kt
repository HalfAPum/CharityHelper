package com.example.planthelper.ui.screen.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.planthelper.models.ui.feed.FeedElement.FeedHeader
import com.example.planthelper.models.ui.feed.FeedElement.FeedItem
import com.example.planthelper.ui.ListSpacer
import com.example.planthelper.ui.theme.LightGreyBackground
import com.example.planthelper.ui.viewmodel.FeedViewModel
import com.example.planthelper.utils.GenericCallback
import org.koin.androidx.compose.getViewModel

@Composable
fun TasksScreen(
    onFeedClicked: GenericCallback<FeedItem>,
    viewModel: FeedViewModel = getViewModel()
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(LightGreyBackground)
            .padding(horizontal = 16.dp)
    ) {
        ListSpacer()

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

        ListSpacer()
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
fun TasksScreenPreview() {
    TasksScreen({})
}