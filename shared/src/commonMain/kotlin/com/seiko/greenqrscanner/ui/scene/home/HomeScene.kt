package com.seiko.greenqrscanner.ui.scene.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.scene.home.content.HomeHistoryContent
import com.seiko.greenqrscanner.ui.scene.home.content.HomeStarContent
import com.seiko.greenqrscanner.ui.widget.BarcodeItemClickable
import com.seiko.greenqrscanner.util.AppLogger
import io.github.seiko.precompose.annotation.NavGraphDestination
import kotlinx.coroutines.launch
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalFoundationApi::class)
@NavGraphDestination(
    route = Route.Home,
)
@Composable
fun HomeScene(
    navigator: Navigator,
) {
    val status by producePresenter { HomePresenter() }
    val scope = rememberCoroutineScope()
    val clickable = remember(status) {
        HomeSceneClickable(
            barcodeItemClickable = BarcodeItemClickable(
                onItemClicked = {
                    AppLogger.d { "onItemClicked $it" }
                    navigator.navigate(
                        Route.Detail(it.rawValue),
                        NavOptions(
                            launchSingleTop = true,
                        ),
                    )
                },
                onStarClicked = {
                    status.setStar(it)
                },
                onSettingClicked = {
                    AppLogger.d { "onSetting $it" }
                    navigator.navigate(
                        Route.Popup.BarcodeSettings(it.rawValue),
                        // NavOptions(
                        //     launchSingleTop = true,
                        // ),
                    )
                },
            ),
        )
    }
    Scaffold { innerPadding ->
        Column(Modifier.padding(innerPadding).fillMaxSize()) {
            val pagerState = rememberPagerState(
                initialPage = status.initialSelectIndex,
                pageCount = { status.homeTabs.size },
            )
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
                state = pagerState,
                key = { it },
                // workaround for crash: java.lang.IllegalStateException: LayoutCoordinate operations are only valid when isAttached is true
                beyondBoundsPageCount = 1,
                modifier = Modifier.weight(1f).fillMaxWidth(),
            ) { page ->
                when (status.homeTabs[page]) {
                    HomeTab.History -> HomeHistoryContent(
                        barcodeItemClickable = clickable.barcodeItemClickable,
                        modifier = Modifier.fillMaxSize(),
                    )
                    HomeTab.Star -> HomeStarContent(
                        barcodeItemClickable = clickable.barcodeItemClickable,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private fun HomePresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): HomeStatus {
    val homeTabs = remember { HomeTab.values().toList() }
    val initialSelectIndex = remember { homeTabs.indexOf(HomeTab.History) }
    val scope = rememberCoroutineScope()
    return HomeStatus(
        initialSelectIndex = initialSelectIndex,
        homeTabs = homeTabs,
        event = object : HomeEvent {
            override fun setStar(item: UiBarcode) {
                scope.launch {
                    barcodeRepository.setStar(
                        barcode = item.rawValue,
                        isStar = !item.isStar,
                    )
                }
            }
        },
    )
}

private interface HomeEvent {
    fun setStar(item: UiBarcode)
}

private data class HomeStatus(
    val initialSelectIndex: Int,
    val homeTabs: List<HomeTab>,
    private val event: HomeEvent,
) : HomeEvent by event

private enum class HomeTab {
    History,
    Star
}

private data class HomeSceneClickable(
    val barcodeItemClickable: BarcodeItemClickable,
)
