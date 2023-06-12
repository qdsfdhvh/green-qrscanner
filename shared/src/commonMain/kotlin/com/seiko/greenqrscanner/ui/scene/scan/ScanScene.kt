package com.seiko.greenqrscanner.ui.scene.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BarcodeScanner
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScene(
    navigator: Navigator,
) {
    Scaffold { innerPadding ->
        BarcodeScanner(
            onResult = { result ->
                result.firstOrNull()?.let {
                    navigator.navigate(
                        Route.Detail(it.rawValue),
                        NavOptions(
                            launchSingleTop = true,
                        ),
                    )
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }
}
