package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.seiko.greenqrscanner.ui.widget.AlertDialog
import moe.tlaster.precompose.navigation.Navigator

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
