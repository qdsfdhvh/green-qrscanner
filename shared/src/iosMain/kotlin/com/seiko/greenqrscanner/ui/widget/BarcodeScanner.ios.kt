package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import com.seiko.greenqrscanner.data.model.Barcode
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVCaptureDeviceInput
import platform.AVFoundation.AVCaptureMetadataOutput
import platform.AVFoundation.AVCaptureSession
import platform.AVFoundation.AVCaptureVideoPreviewLayer
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.CoreGraphics.CGRect
import platform.QuartzCore.CATransaction
import platform.QuartzCore.kCATransactionDisableActions
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun BarcodeScanner(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    CameraPermissionRequiredContent(modifier) { camera ->
        val captureSession = remember {
            AVCaptureSession().apply {
                val videoInput = AVCaptureDeviceInput.deviceInputWithDevice(camera, error = null)!!
                if (canAddInput(videoInput)) {
                    addInput(videoInput)
                }
                val metaDataOutput = AVCaptureMetadataOutput()
                if (canAddOutput(metaDataOutput)) {
                    addOutput(metaDataOutput)
                }
            }
        }
        val cameraPreviewLayer = remember {
            AVCaptureVideoPreviewLayer(session = captureSession).apply {
                videoGravity = AVLayerVideoGravityResizeAspectFill
            }
        }
        UIKitView(
            modifier = Modifier.matchParentSize(),
            background = Color.Black,
            factory = {
                UIView().apply {
                    layer.addSublayer(cameraPreviewLayer)
                    captureSession.startRunning()
                    // TODO support barcode scan
                }
            },
            onResize = { view: UIView, rect: CValue<CGRect> ->
                CATransaction.begin()
                CATransaction.setValue(true, kCATransactionDisableActions)
                view.layer.setFrame(rect)
                cameraPreviewLayer.setFrame(rect)
                CATransaction.commit()
            },
        )
        content()
    }
}
