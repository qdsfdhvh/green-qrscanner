package com.seiko.greenqrscanner.ui.scene.popup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BottomSheet
import com.seiko.greenqrscanner.util.decodeUrl
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Path
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

@Composable
fun BarcodeSettingsBottomSheet(
    navigator: Navigator,
    barcode: String,
) {
    BottomSheet(
        onDismissRequest = { navigator.goBack() },
    ) {
        Column {
            ListItem(
                leadingContent = {
                    Icon(Icons.Rounded.Delete, contentDescription = "delete")
                },
                headlineContent = {
                    Text("删除")
                },
            )
            Spacer(Modifier.height(8.dp))
            Button(onClick = { navigator.goBack() }) {
                Text("Cancel")
            }
        }
    }
}
