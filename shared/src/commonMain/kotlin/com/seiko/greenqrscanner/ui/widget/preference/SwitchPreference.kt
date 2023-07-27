package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.store.DataStoreManager

@Composable
fun SwitchPreference(
    title: @Composable () -> Unit,
    key: String,
    defaultValue: Boolean,
    dataStore: DataStoreManager,
    modifier: Modifier = Modifier,
    description: (@Composable () -> Unit)? = null,
) {
    SwitchPreference(
        title = title,
        checked = remember(key) {
            dataStore.flow(key, defaultValue)
        }.collectAsState(false).value,
        onCheckedChange = { dataStore.putAsync(key, it) },
        modifier = modifier,
        description = description,
    )
}

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
