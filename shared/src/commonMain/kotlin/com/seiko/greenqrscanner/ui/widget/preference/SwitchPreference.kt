package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SwitchPreference(
    title: @Composable () -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    description: (@Composable () -> Unit)? = null,
) {
    ListItem(
        headlineContent = title,
        supportingContent = description,
        trailingContent = {
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
            )
        },
        modifier = modifier.clickable {
            onCheckedChange(!checked)
        },
    )
}
