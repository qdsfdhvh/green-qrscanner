package com.seiko.greenqrscanner.scene

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import compose.icons.TablerIcons
import compose.icons.tablericons.Home
import compose.icons.tablericons.Scan
import compose.icons.tablericons.Settings
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScene() {
    val status by producePresenter { HomePresenter() }
    Scaffold { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize()) {
            HomeBottomBar(
                selectedTabIndex = status.selectedTabIndex,
                tabs = status.homeTabs,
                onTabClick = { tab ->
                    status.event(HomeEvent.SelectTab(tab))
                },
                modifier = Modifier
                    .padding(bottom = 40.dp, start = 60.dp, end = 60.dp)
                    .align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
private fun HomeBottomBar(
    selectedTabIndex: Int,
    tabs: List<HomeTab>,
    onTabClick: (HomeTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        tonalElevation = 0.dp,
        modifier = modifier.clip(CircleShape),
        windowInsets = WindowInsets(0.dp),
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(tab) },
                icon = {
                    Icon(
                        tab.icon,
                        contentDescription = tab.name,
                    )
                }
            )
        }
    }
}

@Composable
private fun HomePresenter(): HomeStatus {
    var selectTab by remember { mutableStateOf(HomeTab.Scan) }
    val homeTabs = remember { HomeTab.values().toList() }
    return HomeStatus(
        selectedTabIndex = homeTabs.indexOf(selectTab),
        homeTabs = homeTabs,
        event = {
            when (it) {
                is HomeEvent.SelectTab -> selectTab = it.tab
            }
        }
    )
}

private sealed interface HomeEvent {
    data class SelectTab(val tab: HomeTab) : HomeEvent
}

private data class HomeStatus(
    val selectedTabIndex: Int,
    val homeTabs: List<HomeTab>,
    val event: (HomeEvent) -> Unit,
)

private enum class HomeTab {
    Home,
    Scan,
    Settings;
}

private val HomeTab.icon: ImageVector
    get() = when (this) {
        HomeTab.Home -> TablerIcons.Home
        HomeTab.Scan -> TablerIcons.Scan
        HomeTab.Settings -> TablerIcons.Settings
    }