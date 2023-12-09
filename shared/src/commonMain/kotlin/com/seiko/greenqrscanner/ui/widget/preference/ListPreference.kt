package com.seiko.greenqrscanner.ui.widget.preference

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.store.DataStoreManager
import com.seiko.greenqrscanner.ui.widget.AlertDialog
import kotlinx.collections.immutable.ImmutableList

@Composable
fun DialogListPreference(
    title: @Composable () -> Unit,
    key: String,
    defaultValueIndex: Int,
    values: ImmutableList<String>,
    dataStore: DataStoreManager,
    modifier: Modifier = Modifier,
) {
    val selectIndexOrNull by remember(key) {
        dataStore.flow(key, defaultValueIndex)
    }.collectAsState(null)
    selectIndexOrNull?.let { selectIndex ->
        DialogListPreference(
            title = title,
            selectIndex = selectIndex,
            onSelectIndexChange = { dataStore.putAsync(key, it) },
            itemCount = values.size,
            valueText = { values[it] },
            modifier = modifier,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogListPreference(
    title: @Composable () -> Unit,
    selectIndex: Int,
    onSelectIndexChange: (Int) -> Unit,
    itemCount: Int,
    valueText: (Int) -> String,
    modifier: Modifier = Modifier,
) {
    var openSelector by rememberSaveable { mutableStateOf(false) }
    ListItem(
        headlineContent = title,
        supportingContent = {
            Text(valueText(selectIndex))
        },
        modifier = modifier.clickable { openSelector = true },
    )
    if (openSelector) {
        AlertDialog(
            onDismissRequest = { openSelector = false },
            confirmButton = {
                TextButton(onClick = { openSelector = false }) {
                    Text("cancel")
                }
            },
            title = title,
            text = {
                Column {
                    repeat(itemCount) { index ->
                        if (index == selectIndex) {
                            Button(onClick = {
                                openSelector = false
                                onSelectIndexChange(index)
                            }, Modifier.fillMaxWidth()) {
                                Text(valueText(index))
                            }
                        } else {
                            TextButton(onClick = {
                                openSelector = false
                                onSelectIndexChange(index)
                            }, Modifier.fillMaxWidth()) {
                                Text(valueText(index))
                            }
                        }
                    }
                }
            },
        )
    }
}
