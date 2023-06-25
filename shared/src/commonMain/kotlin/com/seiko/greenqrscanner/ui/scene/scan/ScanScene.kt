package com.seiko.greenqrscanner.ui.scene.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BarcodeScanner
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.launch
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScanScene(
    navigator: Navigator,
) {
    val status by producePresenter { ScanPresenter() }
    Scaffold { innerPadding ->
        BarcodeScanner(
            onResult = { result ->
                status.upset(result)
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

@Composable
private fun ScanPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): ScanStatus {
    val scope = rememberCoroutineScope()
    return ScanStatus(
        event = object : ScanEvent {
            override fun upset(barcodes: List<Barcode>) {
                scope.launch(NonCancellable) {
                    barcodeRepository.upset(barcodes)
                }
            }
        },
    )
}

private interface ScanEvent {
    fun upset(barcodes: List<Barcode>)
}

private class ScanStatus(
    private val event: ScanEvent,
) : ScanEvent by event
