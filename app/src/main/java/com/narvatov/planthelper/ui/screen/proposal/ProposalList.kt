package com.narvatov.planthelper.ui.screen.proposal

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.narvatov.planthelper.data.utils.LoginStateHolder
import com.narvatov.planthelper.models.ui.tabs
import com.narvatov.planthelper.ui.ListSpacer
import com.narvatov.planthelper.ui.navigation.UiNavigationEventPropagator.navigate
import com.narvatov.planthelper.ui.navigation.CreateProposal
import com.narvatov.planthelper.ui.navigation.Filter
import com.narvatov.planthelper.ui.screen.SearchView
import com.narvatov.planthelper.ui.theme.RegularBlack
import com.narvatov.planthelper.ui.theme.RegularGrey
import com.narvatov.planthelper.ui.viewmodel.ProposalListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProposalList(
    viewModel: ProposalListViewModel = getViewModel()
) = Box(modifier = Modifier.fillMaxSize()) {
    val scope = rememberCoroutineScope()

    var tabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Transparent,
            contentColor = RegularBlack,
            divider = {
                TabRowDefaults.Divider(
                    thickness = 2.dp,
                    color = Color.Green
                )
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            },
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        viewModel.runInit()
                        tabIndex = index

                        scope.launch {
                            pagerState.animateScrollToPage(tabIndex)
                        }
                    },
                    text = {
                        val selected = pagerState.currentPage == index
                        val contentColor = if (selected) RegularBlack else RegularGrey

                        Row(
                            modifier = Modifier
                                .padding(bottom = if (selected) 12.dp else 14.dp)
                                .height(40.dp)
                        ) {
                            Image(
                                painter = painterResource(tab.icon),
                                contentDescription = null,
                                modifier = Modifier.align(Alignment.Bottom),
                                colorFilter = ColorFilter.tint(contentColor)
                            )

                            Text(
                                text = stringResource(tab.title),
                                fontSize = 16.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                                color = contentColor,
                                modifier = Modifier
                                    .align(Alignment.Bottom)
                                    .padding(start = 8.dp),
                            )
                        }
                    },
                )
            }
        }

        val ownProposalList = viewModel.ownProposalSharedFlow.collectAsState(emptyList())
        val publicProposalList = viewModel.publicProposalSharedFlow.collectAsState(emptyList())
        val searchProposalList = viewModel.searchProposalSharedFlow.collectAsState(emptyList())

        HorizontalPager(
            count = tabs.size,
            state = pagerState,
        ) { tabIndex ->
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                ListSpacer()


                val proposals = when(tabIndex) {
                    SEARCH_PROPOSALS -> searchProposalList.value
                    PUBLIC_PROPOSALS -> publicProposalList.value
                    OWN_PROPOSALS -> ownProposalList.value
                    else -> emptyList()
                }


                if (tabIndex == SEARCH_PROPOSALS) {
                    item {
                        SearchView(viewModel)
                    }
                }

                items(proposals) { proposal ->
                    ProposalListItem(proposal)
                }

                ListSpacer()
            }
        }
    }

    if (LoginStateHolder.isLoggedIn) {
        FloatingActionButton(
            onClick = { navigate(CreateProposal) },
            modifier = Modifier.align(Alignment.BottomEnd).padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null
            )
        }
    }

    if (tabIndex == SEARCH_PROPOSALS) {
        FloatingActionButton(
            onClick = { navigate(Filter) },
            modifier = Modifier.align(Alignment.BottomStart).padding(24.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.FilterAlt,
                contentDescription = null
            )
        }
    }

}

private const val PUBLIC_PROPOSALS = 1
private const val OWN_PROPOSALS = 2
private const val SEARCH_PROPOSALS = 0