package com.seiko.greenqrscanner.ui.widget

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.seiko.greenqrscanner.option.PermissionPlace

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionRequiredContent(
    permissionPlace: PermissionPlace,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    val permissionsState = rememberMultiplePermissionsState(permissionPlace.permissions)
    Box(modifier) {
        when {
            permissionsState.allPermissionsGranted -> {
                content()
            }
            else -> {
                val context = LocalContext.current
                PermissionDeniedContent(
                    permissionPlace = permissionPlace,
                    requestButton = {
                        Button(onClick = {
                            if (permissionsState.shouldShowRationale) {
                                context.startActivity(
                                    Intent(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", context.packageName, null),
                                    ),
                                )
                            } else {
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }) {
                            Text(
                                if (permissionsState.shouldShowRationale) {
                                    "Go to Settings"
                                } else {
                                    "OK"
                                },
                            )
                        }
                    },
                    modifier = Modifier.matchParentSize(),
                )
            }
        }
    }
}

private val PermissionPlace.permissions: List<String>
    get() = when (this) {
        PermissionPlace.QrScan -> listOf(android.Manifest.permission.CAMERA)
    }
