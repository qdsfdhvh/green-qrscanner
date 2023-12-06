package com.seiko.greenqrscanner.ui.scene.scan

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BarcodeScanner
import io.github.seiko.precompose.annotation.NavGraphDestination
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@NavGraphDestination(
    route = Route.Scan,
)
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
    return ScanStatus(
        event = object : ScanEvent {
            override fun upset(barcodes: List<Barcode>) {
                barcodeRepository.upset(barcodes)
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
