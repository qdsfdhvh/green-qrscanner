package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PreferenceTitle(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            ProvideTextStyle(
                MaterialTheme.typography.titleMedium,
                content = title,
            )
        },
        modifier = modifier,
    )
}
