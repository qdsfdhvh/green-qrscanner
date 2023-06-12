package com.seiko.greenqrscanner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Barcode(
    val rawValue: String,
    val format: BarcodeFormat,
    val type: BarcodeType,
)
