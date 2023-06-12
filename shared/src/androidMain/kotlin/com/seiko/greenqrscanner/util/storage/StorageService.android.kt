package com.seiko.greenqrscanner.util.storage

import android.content.Context
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
actual class StorageService(context: Context) {
    actual val appDir: String = "${context.filesDir.absolutePath}/app"
    actual val cacheDir: String = "${context.cacheDir.absolutePath}/caches"
}
