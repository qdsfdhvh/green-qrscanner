package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Preference(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    summary: (@Composable () -> Unit)? = null,
    trail: (@Composable () -> Unit)? = null,
    tonalElevation: Dp = ListItemDefaults.Elevation,
    shadowElevation: Dp = ListItemDefaults.Elevation,
) {
    ListItem(
        headlineContent = title,
        modifier = modifier,
        supportingContent = summary,
        trailingContent = trail,
        tonalElevation = tonalElevation,
        shadowElevation = shadowElevation,
    )
}

@Composable
fun Preference(
    title: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = title,
        modifier = modifier.clickable(onClick = onClick),
    )
}
