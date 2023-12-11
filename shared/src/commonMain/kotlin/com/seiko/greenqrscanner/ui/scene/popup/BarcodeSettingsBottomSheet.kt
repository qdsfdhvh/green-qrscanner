package com.seiko.greenqrscanner.ui.scene.popup

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.util.decodeUrl
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Path
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.Navigator

@NavGraphDestination(
    route = Route.Popup.BarcodeSettings.path,
    functionName = "floating",
)
@Composable
fun BarcodeSettingsBottomSheetRoute(
    navigator: Navigator,
    @Path(Route.paramBarcode) barcodeBase64: String,
) {
    BarcodeSettingsBottomSheet(
        navigator = navigator,
        barcode = remember { barcodeBase64.decodeUrl() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarcodeSettingsBottomSheet(
    navigator: Navigator,
    barcode: String,
) {
    val status by producePresenter { BarcodeSettingsPresenter() }
    ModalBottomSheet(
        onDismissRequest = { navigator.goBack() },
    ) {
        ListItem(
            leadingContent = {
                Icon(Icons.Rounded.Delete, contentDescription = "delete")
            },
            headlineContent = {
                Text(
                    "删除 $barcode",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            modifier = Modifier.clickable {
                navigator.popBackStack()
                status.delete(barcode)
            },
        )
        Spacer(Modifier.height(8.dp))
        Button(
            onClick = { navigator.goBack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            Text("Cancel")
        }
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun BarcodeSettingsPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): BarcodeSettingsStatus {
    return BarcodeSettingsStatus(
        event = object : BarcodeSettingsEvent {
            override fun delete(barcode: String) {
                barcodeRepository.delete(barcode)
            }
        },
    )
}

private interface BarcodeSettingsEvent {
    fun delete(barcode: String)
}

private class BarcodeSettingsStatus(
    private val event: BarcodeSettingsEvent,
) : BarcodeSettingsEvent by event
