package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun BarcodeScanner(
    onResult: (result: String) -> Unit,
    modifier: Modifier,
) {
    // TODO support BarcodeScanner in ios
}