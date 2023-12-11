package com.seiko.greenqrscanner.ui.scene.scan

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.scene.scan.widget.ScanCropView
import com.seiko.greenqrscanner.ui.widget.BarcodeScanner
import io.github.seiko.precompose.annotation.NavGraphDestination
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

@NavGraphDestination(
    route = Route.Scan,
)
@Composable
fun ScanScene(
    navigator: Navigator,
) {
    val status by producePresenter { ScanPresenter() }
    DisposableEffect(Unit) {
        onDispose {
            status.resetScan()
        }
    }
    Scaffold { innerPadding ->
        if (status.isOpenScan) {
            BarcodeScanner(
                onResult = { result ->
                    status.upset(result)
                    result.firstOrNull()?.let {
                        navigator.navigate(
                            Route.Detail(it.rawValue),
                            NavOptions(
                                launchSingleTop = true,
                                popUpTo = PopUpTo.First(false),
                            ),
                        )
                    }
                },
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                ScanCropView(
                    modifier = Modifier.fillMaxSize(),
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Button(onClick = { status.openScan() }) {
                    Text("Scan")
                }
            }
        }
    }
}

@Composable
private fun ScanPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): ScanStatus {
    var isOpenScan by remember { mutableStateOf(false) }
    return ScanStatus(
        isOpenScan = isOpenScan,
        event = object : ScanEvent {
            override fun resetScan() {
                isOpenScan = false
            }

            override fun openScan() {
                isOpenScan = true
            }

            override fun upset(barcodes: List<Barcode>) {
                barcodeRepository.upset(barcodes)
            }
        },
    )
}

private interface ScanEvent {
    fun resetScan()
    fun openScan()
    fun upset(barcodes: List<Barcode>)
}

private class ScanStatus(
    val isOpenScan: Boolean,
    private val event: ScanEvent,
) : ScanEvent by event
