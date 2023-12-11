package com.seiko.greenqrscanner.util

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.compose.runtime.Immutable
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import com.seiko.greenqrscanner.data.mapper.MlkitBarcode
import com.seiko.greenqrscanner.data.mapper.toBarcode
import com.seiko.greenqrscanner.data.model.Barcode

@Immutable
class MlKitBarcodeAnalyzer(
    private val onBarcodeScanned: (List<Barcode>) -> Unit,
) : ImageAnalysis.Analyzer {

    private val options = BarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            // 2D
            MlkitBarcode.FORMAT_QR_CODE,
            MlkitBarcode.FORMAT_AZTEC,
            MlkitBarcode.FORMAT_PDF417,
            MlkitBarcode.FORMAT_DATA_MATRIX,
            // 1D
            MlkitBarcode.FORMAT_CODABAR,
            MlkitBarcode.FORMAT_CODE_39,
            MlkitBarcode.FORMAT_CODE_93,
            MlkitBarcode.FORMAT_CODE_128,
            MlkitBarcode.FORMAT_EAN_8,
            MlkitBarcode.FORMAT_EAN_13,
            MlkitBarcode.FORMAT_UPC_A,
            MlkitBarcode.FORMAT_UPC_E,
            MlkitBarcode.FORMAT_ITF,
        )
        .build()

    private val scanner = BarcodeScanning.getClient(options)

    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        imageProxy.toInputImage()?.let { inputImage ->
            scanner.process(inputImage)
                .addOnSuccessListener {
                    onBarcodeScanned(it.toBarcode())
                }
                .addOnFailureListener {
                    Log.e("MlKitBarcodeAnalyzer", it.message.orEmpty())
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        }
    }

    @SuppressLint("UnsafeOptInUsageError")
    private fun ImageProxy.toInputImage(): InputImage? {
        return image?.let {
            InputImage.fromMediaImage(it, imageInfo.rotationDegrees)
        }
    }
}
