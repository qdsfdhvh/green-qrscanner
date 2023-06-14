package com.seiko.greenqrscanner.ui.scene.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.scene.home.content.HomeHistoryContent
import com.seiko.greenqrscanner.ui.scene.home.content.HomeStarContent
import kotlinx.coroutines.launch
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScene(
    navigator: Navigator,
) {
    val status by producePresenter { HomePresenter() }
    val scope = rememberCoroutineScope()
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding).fillMaxSize()) {
            val pagerState = rememberPagerState(status.initialSelectIndex)
            TabRow(
                selectedTabIndex = pagerState.currentPage,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                    )
                },
                divider = {},
                modifier = Modifier.fillMaxWidth(),
            ) {
                status.homeTabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == pagerState.currentPage,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(tab.name) },
                    )
                }
            }
            HorizontalPager(
                pageCount = status.homeTabs.size,
                state = pagerState,
                modifier = Modifier.weight(1f).fillMaxWidth(),
            ) { page ->
                when (status.homeTabs[page]) {
                    HomeTab.History -> HomeHistoryContent(
                        onBarcodeClick = {
                            navigator.navigate(
                                Route.Detail(it.rawValue),
                                NavOptions(
                                    launchSingleTop = true,
                                ),
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                    )
                    HomeTab.Star -> HomeStarContent(
                        onBarcodeClick = {
                            navigator.navigate(
                                Route.Detail(it.rawValue),
                                NavOptions(
                                    launchSingleTop = true,
                                ),
                            )
                        },
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private fun HomePresenter(): HomeStatus {
    val homeTabs = remember { HomeTab.values().toList() }
    val initialSelectIndex = remember { homeTabs.indexOf(HomeTab.History) }
    return HomeStatus(
        initialSelectIndex = initialSelectIndex,
        homeTabs = homeTabs,
    )
}

private data class HomeStatus(
    val initialSelectIndex: Int,
    val homeTabs: List<HomeTab>,
)

private enum class HomeTab {
    History,
    Star
}
