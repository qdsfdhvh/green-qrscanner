package com.seiko.greenqrscanner.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.QrCodeScanner
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.ui.HomeTab
import com.seiko.greenqrscanner.ui.Route
import kotlinx.collections.immutable.PersistentList

@Composable
fun SimpleBottomBar(
    tabs: PersistentList<HomeTab>,
    selectedTabIndex: Int,
    onItemClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .windowInsetsPadding(
                WindowInsets.navigationBars.only(WindowInsetsSides.Bottom),
            )
            .height(56.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        tabs.forEachIndexed { index, tab ->
            TabItem(
                tab = tab,
                selected = selectedTabIndex == index,
                onItemClick = { onItemClick(tab.route) },
            )
        }
        AddItem(
            onItemClick = { onItemClick(Route.SelectAdd) },
        )
    }
}

@Composable
private fun RowScope.TabItem(
    tab: HomeTab,
    selected: Boolean,
    onItemClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .weight(1f)
            .fillMaxHeight()
            .clickable(onClick = onItemClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val contentColor = if (selected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.outline
        }
        Icon(
            tab.icon,
            contentDescription = tab.name,
            tint = contentColor,
        )
        AnimatedVisibility(visible = selected) {
            Divider(
                modifier = Modifier
                    .width(20.dp)
                    .padding(top = 4.dp)
                    .clip(shape = MaterialTheme.shapes.small),
                color = contentColor,
                thickness = 2.dp,
            )
        }
    }
}

@Composable
private fun RowScope.AddItem(
    onItemClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(shape = MaterialTheme.shapes.small)
            .weight(1f)
            .fillMaxHeight()
            .clickable(onClick = onItemClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            Icons.Rounded.AddCircle,
            contentDescription = "Add",
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}

private val HomeTab.icon: ImageVector
    get() = when (this) {
        HomeTab.Home -> Icons.Rounded.Home
        HomeTab.Scan -> Icons.Rounded.QrCodeScanner
        HomeTab.Settings -> Icons.Rounded.Settings
    }
