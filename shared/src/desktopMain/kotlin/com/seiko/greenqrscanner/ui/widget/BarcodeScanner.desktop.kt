package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.model.Barcode

@Composable
actual fun BarcodeScanner(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier,
) {
}
