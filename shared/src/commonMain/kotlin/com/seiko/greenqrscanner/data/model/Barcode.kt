package com.seiko.greenqrscanner.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Barcode(
    val rawValue: String,
    val format: BarcodeFormat,
    val type: BarcodeType,
) {
    // constructor(
    //     type: BarcodeType
    // ) : this(
    //     rawValue = type.rawValue,
    //     format = type.format,
    //     type = type,
    // )
}

// private val BarcodeType.rawValue: String
//     get() = TODO("")
//
// private val BarcodeType.format: BarcodeFormat
//     get() = when (this) {
//         BarcodeType.ISBN,
//         BarcodeType.Product -> BarcodeFormat.FORMAT_1D
//         else -> BarcodeFormat.FORMAT_2D
//     }
