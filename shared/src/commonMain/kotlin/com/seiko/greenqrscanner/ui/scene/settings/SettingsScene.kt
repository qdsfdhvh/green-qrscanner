package com.seiko.greenqrscanner.ui.scene.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.store.DataStoreKeys
import com.seiko.greenqrscanner.data.store.DataStoreManager
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.preference.Preference
import com.seiko.greenqrscanner.ui.widget.preference.PreferenceTitle
import com.seiko.greenqrscanner.ui.widget.preference.SwitchPreference
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Navigate

@NavGraphDestination(
    route = Route.Settings,
)
@Composable
fun SettingsScene(
    @Navigate onNavigate: (String) -> Unit,
) {
    Scaffold { innerPadding ->
        val dataStoreManager: DataStoreManager = rememberInject()
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
        ) {
            item {
                PreferenceTitle(
                    title = { Text("Custom") },
                )
            }
            item {
                SwitchPreference(
                    title = { Text("将扫描添加到历史") },
                    dataStore = dataStoreManager,
                    key = DataStoreKeys.save_barcode_to_history,
                    defaultValue = true,
                )
            }
            item {
                SwitchPreference(
                    title = { Text("振动") },
                    dataStore = dataStoreManager,
                    key = DataStoreKeys.vibrate_on_scan,
                    defaultValue = false,
                )
            }
            item {
                SwitchPreference(
                    title = { Text("提示音") },
                    dataStore = dataStoreManager,
                    key = DataStoreKeys.sound_on_scan,
                    defaultValue = false,
                )
            }
            item {
                PreferenceTitle(
                    title = { Text("About") },
                )
            }
            item {
                Preference(
                    title = { Text("About Libraries") },
                    onClick = { onNavigate(Route.AboutLibraries) },
                )
            }
        }
    }
}
