package com.seiko.greenqrscanner.util

import androidx.compose.ui.graphics.ImageBitmap

expect fun generateQrCode(content: String, size: Int): ImageBitmap
