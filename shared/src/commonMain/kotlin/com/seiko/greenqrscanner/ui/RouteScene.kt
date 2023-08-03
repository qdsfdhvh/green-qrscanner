package com.seiko.greenqrscanner.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.ui.widget.SimpleBottomBar
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
            modifier = Modifier
                .padding(bottom = if (showBottomBar) 56.dp else 0.dp)
                .fillMaxSize(),
            navigator = navigator,
            initialRoute = Route.initial,
            navTransition = noneTransition,
        ) {
            generateRoute(navigator)
        }
        AnimatedVisibility(
            visible = showBottomBar,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it },
            exit = slideOutVertically { it },
        ) {
            SimpleBottomBar(
                tabs = homeTabs,
                selectedTabIndex = selectedTabIndex,
                onItemClick = { route ->
                    navigator.navigate(route, NavOptions(launchSingleTop = true))
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

enum class HomeTab(val route: String) {
    Home(Route.Home),
    Scan(Route.Scan),
    Settings(Route.Settings)
}
