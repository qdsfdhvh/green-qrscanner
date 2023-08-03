package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.AlertDialog
import com.seiko.greenqrscanner.util.decodeUrl
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Path
import moe.tlaster.precompose.navigation.Navigator

@NavGraphDestination(
    route = Route.DetailFullContent.path,
    functionName = "floating",
)
@Composable
fun DetailContentDialogRoute(
    navigator: Navigator,
    @Path(Route.paramBarcode) barcodeBase64: String,
) {
    DetailContentDialog(
        navigator = navigator,
        barcode = remember { barcodeBase64.decodeUrl() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContentDialog(
    navigator: Navigator,
    barcode: String,
) {
    AlertDialog(
        onDismissRequest = { navigator.goBack() },
        confirmButton = {
            Button(onClick = { navigator.goBack() }) {
                Text(text = "cancel")
            }
        },
        text = {
            SelectionContainer {
                Text(barcode)
            }
        },
    )
}
