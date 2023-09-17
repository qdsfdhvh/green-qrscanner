package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
