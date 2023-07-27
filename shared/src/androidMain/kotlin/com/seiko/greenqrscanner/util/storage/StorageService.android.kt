package com.seiko.greenqrscanner.util.storage

import android.content.Context
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import okio.Path
import okio.Path.Companion.toOkioPath

@Singleton
@Provides
actual class StorageService(context: Context) {
    actual val appDir: Path = context.filesDir.toOkioPath().resolve("app")
    actual val cacheDir: Path = context.cacheDir.toOkioPath().resolve("caches")
}
