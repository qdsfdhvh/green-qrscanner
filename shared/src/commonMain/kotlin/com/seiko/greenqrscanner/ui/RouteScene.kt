package com.seiko.greenqrscanner.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.mapNotNull
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun RouteScene(
    navigator: Navigator,
    modifier: Modifier = Modifier,
) {
    val homeTabs = remember { persistentListOf(*HomeTab.values()) }
    val selectedTabIndex by remember {
        navigator.currentEntry.mapNotNull { backStackEntry ->
            val route = backStackEntry?.route?.route ?: return@mapNotNull null
            homeTabs.indexOfFirst { it.route == route }.takeUnless { it < 0 }
        }
    }.collectAsState(-1)
    val showBottomBar by remember {
        navigator.currentEntry.mapNotNull { backStackEntry ->
            val route = backStackEntry?.route?.route ?: return@mapNotNull null
            homeTabs.any { it.route == route }
        }
    }.collectAsState(true)

    Box(modifier) {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navigator = navigator,
            initialRoute = Route.initial,
            navTransition = noneTransition,
        ) {
            initRoute(navigator)
        }
        AnimatedVisibility(
            visible = showBottomBar,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            HomeBottomBar(
                selectedTabIndex = selectedTabIndex,
                tabs = homeTabs,
                onTabClick = { tab ->
                    navigator.navigate(tab.route, NavOptions(launchSingleTop = true))
                },
                modifier = Modifier.padding(bottom = 40.dp, start = 60.dp, end = 60.dp),
            )
        }
    }
}

@Composable
private fun HomeBottomBar(
    selectedTabIndex: Int,
    tabs: ImmutableList<HomeTab>,
    onTabClick: (HomeTab) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        tonalElevation = 0.dp,
        modifier = modifier.clip(CircleShape),
        windowInsets = WindowInsets(0.dp),
        containerColor = Color.Black,
        contentColor = Color.White,
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedTabIndex == index,
                onClick = { onTabClick(tab) },
                icon = {
                    Icon(
                        tab.icon,
                        contentDescription = tab.name,
                        modifier = Modifier.size(24.dp),
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = Color.White,
                    unselectedTextColor = Color.White,
                ),
            )
        }
    }
}

private enum class HomeTab {
    Home,
    Scan,
    Settings
}

private val HomeTab.route: String
    get() = when (this) {
        HomeTab.Home -> Route.Home
        HomeTab.Scan -> Route.Scan
        HomeTab.Settings -> Route.Settings
    }

private val HomeTab.icon: ImageVector
    get() = when (this) {
        HomeTab.Home -> Icons.Rounded.Home
        HomeTab.Scan -> Icons.Rounded.QrCodeScanner
        HomeTab.Settings -> Icons.Rounded.Settings
    }
