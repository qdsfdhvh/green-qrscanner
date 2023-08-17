package com.seiko.greenqrscanner.util.storage

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDirectory
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
@Singleton
@Provides
actual class StorageService {

    private val _appDir by lazy {
        NSFileManager.defaultManager.URLForDirectory(
            NSUserDirectory,
            NSUserDomainMask,
            null,
            true,
            null,
        )!!
    }

    private val _cacheDir by lazy {
        NSFileManager.defaultManager.URLForDirectory(
            NSCachesDirectory,
            NSUserDomainMask,
            null,
            true,
            null,
        )!!
    }

    actual val appDir: Path
        get() = requireNotNull(_appDir.path).toPath()

    actual val cacheDir: Path
        get() = requireNotNull(_cacheDir.path).toPath()
}
