package com.seiko.greenqrscanner.data.store

object DataStoreKeys {
    const val theme_type = "${prefix}theme_type"
    const val save_barcode_to_history = "${prefix}save_barcode_to_history"
    const val vibrate_on_scan = "${prefix}vibrate_on_scan"
    const val sound_on_scan = "${prefix}sound_on_scan"
}

private const val prefix = "app_key."
