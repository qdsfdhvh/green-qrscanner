package com.seiko.greenqrscanner.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BarcodeScanner(
    onResult: (result: String) -> Unit,
    modifier: Modifier = Modifier,
)
