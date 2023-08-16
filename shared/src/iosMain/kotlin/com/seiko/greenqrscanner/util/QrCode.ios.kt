/**
 * Taken from https://github.com/javokhirsavriev/qr-project/blob/main/composeApp/src/iosMain/kotlin/uz/qrscanner/qrcodescanner/barcodereader/barcodescanner/view/QrPainter.ios.kt
 */
package com.seiko.greenqrscanner.util

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.CoreImage.CIContext
import platform.CoreImage.CIFilter
import platform.CoreImage.QRCodeGenerator
import platform.CoreImage.createCGImage
import platform.Foundation.setValue

@OptIn(ExperimentalForeignApi::class)
actual fun generateQrCode(content: String, size: Int): ImageBitmap {
    val filter = CIFilter.QRCodeGenerator().apply {
        setValue(content.nsData(), forKey = "inputMessage")
    }
    val outputImage = filter.outputImage ?: error("failed to generate qrcode")

    val scale = (size / outputImage.extent.size).toDouble()
    val transformedImage = outputImage.imageByApplyingTransform(
        CGAffineTransformMakeScale(scale, scale),
    )

    val context = CIContext()
    val cgImage = context.createCGImage(transformedImage, transformedImage.extent)
        ?: error("can't read CGImage for generate qrcode with content $content")

    return cgImage.toSkiaImage().toComposeImageBitmap()
}
