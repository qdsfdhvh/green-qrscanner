package com.seiko.greenqrscanner.ui.widget

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.option.PermissionPlace
import com.seiko.greenqrscanner.util.MlKitBarcodeAnalyzer
import java.util.concurrent.Executors

@Composable
actual fun BarcodeScanner(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier,
) {
    PermissionRequiredContent(
        permissionPlace = PermissionPlace.QrScan,
        modifier = modifier,
    ) {
        BarcodeScannerContent(
            onResult = onResult,
            modifier = Modifier.matchParentSize(),
        )
    }
}

@Composable
private fun BarcodeScannerContent(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val cameraProvider by produceState<ProcessCameraProvider?>(null) {
        ProcessCameraProvider.getInstance(context).also { cameraProvider ->
            cameraProvider.addListener({
                value = cameraProvider.get()
            }, ContextCompat.getMainExecutor(context))
        }
    }

    val preview = remember {
        Preview.Builder().build()
    }

    val previewView = remember {
        PreviewView(context)
    }

    val cameraSelector = remember {
        CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()
    }

    val imageAnalysis = remember {
        ImageAnalysis.Builder()
            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
            .build()
            .apply {
                setAnalyzer(
                    Executors.newSingleThreadExecutor(),
                    MlKitBarcodeAnalyzer { result ->
                        onResult(result)
                    },
                )
            }
    }

    val lifecycleOwner = rememberCameraLifecycleOwner()

    LaunchedEffect(cameraProvider) {
        cameraProvider?.let { camera ->
            camera.unbindAll()
            camera.bindToLifecycle(
                lifecycleOwner,
                cameraSelector,
                preview,
                imageAnalysis,
            )
            preview.setSurfaceProvider(previewView.surfaceProvider)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraProvider?.unbindAll()
        }
    }

    AndroidView(
        factory = { previewView },
        modifier = modifier,
    )
}

@Composable
private fun rememberCameraLifecycleOwner(): CameraLifecycleOwner {
    val lifecycleOwner = remember { CameraLifecycleOwner() }
    DisposableEffect(lifecycleOwner) {
        lifecycleOwner.bind()
        onDispose {
            lifecycleOwner.unbind()
        }
    }
    return lifecycleOwner
}

private class CameraLifecycleOwner : androidx.lifecycle.LifecycleOwner {

    private val lifecycleRegistry = androidx.lifecycle.LifecycleRegistry(this)

    fun bind() {
        lifecycleRegistry.currentState = androidx.lifecycle.Lifecycle.State.RESUMED
    }

    fun unbind() {
        lifecycleRegistry.currentState = androidx.lifecycle.Lifecycle.State.DESTROYED
    }

    override val lifecycle: androidx.lifecycle.Lifecycle
        get() = lifecycleRegistry
}
