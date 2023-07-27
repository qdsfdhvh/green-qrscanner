package com.seiko.greenqrscanner.ui.scene.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.ui.widget.preference.SwitchPreference

@Composable
fun SettingsScene() {
    Scaffold { innerPadding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
        ) {
            item {
                var saveBarcodeToHistory by rememberSaveable { mutableStateOf(true) }
                SwitchPreference(
                    title = { Text("将扫描添加到历史") },
                    checked = saveBarcodeToHistory,
                    onCheckedChange = { saveBarcodeToHistory = it },
                )
            }
            item {
                var vibrateOnScan by rememberSaveable { mutableStateOf(false) }
                SwitchPreference(
                    title = { Text("振动") },
                    checked = vibrateOnScan,
                    onCheckedChange = { vibrateOnScan = it },
                )
            }
            item {
                var soundOnScan by rememberSaveable { mutableStateOf(false) }
                SwitchPreference(
                    title = { Text("提示音") },
                    checked = soundOnScan,
                    onCheckedChange = { soundOnScan = it },
                )
            }
        }
    }
}
