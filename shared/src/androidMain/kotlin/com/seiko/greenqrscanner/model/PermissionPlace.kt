package com.seiko.greenqrscanner.model

enum class PermissionPlace(
    val permissions: List<String>,
    val title: String,
    val desc: String,
) {
    QrScan(
        permissions = listOf(android.Manifest.permission.CAMERA),
        title = "Allow Camera Access",
        desc = "We need your permission to QR Scan"
    );
}
