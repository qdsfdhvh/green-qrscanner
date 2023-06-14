package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.option.PermissionPlace
import moe.tlaster.precompose.lifecycle.Lifecycle
import moe.tlaster.precompose.lifecycle.LifecycleObserver
import moe.tlaster.precompose.lifecycle.LocalLifecycleOwner
import platform.AVFoundation.AVAuthorizationStatusAuthorized
import platform.AVFoundation.AVAuthorizationStatusDenied
import platform.AVFoundation.AVAuthorizationStatusNotDetermined
import platform.AVFoundation.AVAuthorizationStatusRestricted
import platform.AVFoundation.AVCaptureDevice
import platform.AVFoundation.AVCaptureDeviceDiscoverySession
import platform.AVFoundation.AVCaptureDevicePositionFront
import platform.AVFoundation.AVCaptureDeviceTypeBuiltInDualCamera
import platform.AVFoundation.AVCaptureDeviceTypeBuiltInDualWideCamera
import platform.AVFoundation.AVCaptureDeviceTypeBuiltInDuoCamera
import platform.AVFoundation.AVCaptureDeviceTypeBuiltInUltraWideCamera
import platform.AVFoundation.AVCaptureDeviceTypeBuiltInWideAngleCamera
import platform.AVFoundation.AVMediaTypeVideo
import platform.AVFoundation.authorizationStatusForMediaType
import platform.AVFoundation.requestAccessForMediaType
import platform.Foundation.NSURL.Companion.URLWithString
import platform.UIKit.UIApplication

@Composable
fun CameraPermissionRequiredContent(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.(AVCaptureDevice) -> Unit,
) {
    var cameraAccess: CameraAccess by remember { mutableStateOf(checkCameraAccess) }

    val permissionsCheckerObserver = remember {
        object : LifecycleObserver {
            override fun onStateChanged(state: Lifecycle.State) {
                if (state == Lifecycle.State.Active) {
                    cameraAccess = checkCameraAccess
                }
            }
        }
    }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, permissionsCheckerObserver) {
        lifecycle.addObserver(permissionsCheckerObserver)
        onDispose {
            lifecycle.removeObserver(permissionsCheckerObserver)
        }
    }

    Box(modifier, Alignment.Center) {
        when (val currentCameraAccess = cameraAccess) {
            CameraAccess.Authorized -> {
                val camera: AVCaptureDevice? = remember {
                    AVCaptureDeviceDiscoverySession.discoverySessionWithDeviceTypes(
                        deviceTypes = deviceTypes,
                        mediaType = AVMediaTypeVideo,
                        position = AVCaptureDevicePositionFront,
                    ).devices.firstOrNull() as? AVCaptureDevice
                }
                if (camera != null) {
                    content(camera)
                } else {
                    Text(
                        "Camera is not available on simulator.\n" +
                            "Please try to run on a real iOS device.",
                    )
                }
            }
            is CameraAccess.NotDetermined -> {
                PermissionDeniedContent(
                    permissionPlace = PermissionPlace.QrScan,
                    requestButton = {
                        Button(onClick = {
                            if (currentCameraAccess.shouldGoToSetting) {
                                UIApplication.sharedApplication.openURL(
                                    URLWithString("App-prefs:root=Privacy&path=CAMERA")!!,
                                )
                            } else {
                                AVCaptureDevice.requestAccessForMediaType(
                                    mediaType = AVMediaTypeVideo,
                                ) { success ->
                                    cameraAccess = if (success) {
                                        CameraAccess.Authorized
                                    } else {
                                        CameraAccess.NotDetermined(true)
                                    }
                                }
                            }
                        }) {
                            Text(
                                if (currentCameraAccess.shouldGoToSetting) {
                                    "Go to Settings"
                                } else {
                                    "OK"
                                },
                            )
                        }
                    },
                )
            }
        }
    }
}

private val checkCameraAccess: CameraAccess
    get() = when (AVCaptureDevice.authorizationStatusForMediaType(AVMediaTypeVideo)) {
        AVAuthorizationStatusAuthorized -> {
            CameraAccess.Authorized
        }
        AVAuthorizationStatusDenied,
        AVAuthorizationStatusRestricted -> {
            CameraAccess.NotDetermined(true)
        }
        AVAuthorizationStatusNotDetermined -> {
            CameraAccess.NotDetermined(false)
        }
        else -> {
            CameraAccess.NotDetermined(false)
        }
    }

private val deviceTypes = listOf(
    AVCaptureDeviceTypeBuiltInWideAngleCamera,
    AVCaptureDeviceTypeBuiltInDualWideCamera,
    AVCaptureDeviceTypeBuiltInDualCamera,
    AVCaptureDeviceTypeBuiltInUltraWideCamera,
    AVCaptureDeviceTypeBuiltInDuoCamera,
)

private sealed interface CameraAccess {
    data class NotDetermined(val shouldGoToSetting: Boolean) : CameraAccess
    object Authorized : CameraAccess
}
