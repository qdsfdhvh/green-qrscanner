package com.seiko.greenqrscanner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class BarcodeFormat {
    @SerialName("unknown")
    UNKNOWN,

    @SerialName("1D")
    FORMAT_1D,

    @SerialName("2D")
    FORMAT_2D
}
