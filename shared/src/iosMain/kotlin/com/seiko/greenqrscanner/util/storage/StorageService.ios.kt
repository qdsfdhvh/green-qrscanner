package com.seiko.greenqrscanner.util.storage

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDirectory
import platform.Foundation.NSUserDomainMask

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

    actual val appDir: String
        get() = _appDir.path.orEmpty()

    actual val cacheDir: String
        get() = _cacheDir.path.orEmpty()
}
