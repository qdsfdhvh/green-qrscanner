package com.seiko.greenqrscanner.ui.widget

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.core.resolutionselector.ResolutionSelector
import androidx.camera.core.resolutionselector.ResolutionStrategy
import androidx.camera.core.resolutionselector.ResolutionStrategy.FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.option.PermissionPlace
import com.seiko.greenqrscanner.util.MlKitBarcodeAnalyzer

@Composable
actual fun BarcodeScanner(
    onResult: (result: List<Barcode>) -> Unit,
    modifier: Modifier,
) {
    PermissionRequiredContent(
        permissionPlace = PermissionPlace.QrScan,
        modifier = modifier,
    ) {
        val lifecycleOwner = rememberCameraLifecycleOwner()
        AndroidView(
            factory = { context ->
                PreviewView(context).also {
                    val cameraProviderFeature = ProcessCameraProvider.getInstance(context)

                    val preview = Preview.Builder().build()
                    preview.setSurfaceProvider(it.surfaceProvider)
                    val selector = CameraSelector.Builder()
                        .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                        .build()
                    val imageAnalysis = ImageAnalysis.Builder()
                        .setResolutionSelector(
                            ResolutionSelector.Builder()
                                .setResolutionStrategy(
                                    ResolutionStrategy(
                                        Size(it.width, it.height),
                                        FALLBACK_RULE_CLOSEST_HIGHER_THEN_LOWER,
                                    ),
                                )
                                .build(),
                        )
                        .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                    imageAnalysis.setAnalyzer(
                        ContextCompat.getMainExecutor(context),
                        MlKitBarcodeAnalyzer { result ->
                            onResult(result)
                        },
                    )
                    try {
                        cameraProviderFeature.get().bindToLifecycle(
                            lifecycleOwner,
                            selector,
                            preview,
                            imageAnalysis,
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
    }
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
