package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.model.Barcode

@Composable
expect fun BarcodeScanner(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
)
