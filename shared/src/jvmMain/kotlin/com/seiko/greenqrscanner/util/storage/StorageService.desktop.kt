package com.seiko.greenqrscanner.util.storage

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.util.OperatingSystem
import com.seiko.greenqrscanner.util.currentOperatingSystem
import java.io.File

@Singleton
@Provides
actual class StorageService {
    actual val appDir: String
        get() = getAppDir().absolutePath
    actual val cacheDir: String
        get() = getCacheDir().absolutePath
}

private fun getAppDir() = when (currentOperatingSystem) {
    OperatingSystem.Windows -> File(System.getenv("AppData"), "$ApplicationName/app")
    OperatingSystem.Linux -> File(System.getProperty("user.home"), ".config/$ApplicationName")
    OperatingSystem.MacOS -> File(
        System.getProperty("user.home"),
        "Library/Application Support/$ApplicationName",
    )
    else -> throw IllegalStateException("Unsupported operating system")
}

private fun getCacheDir() = when (currentOperatingSystem) {
    OperatingSystem.Windows -> File(System.getenv("AppData"), "$ApplicationName/cache")
    OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/$ApplicationName")
    OperatingSystem.MacOS -> File(
        System.getProperty("user.home"),
        "Library/Caches/$ApplicationName",
    )
    else -> throw IllegalStateException("Unsupported operating system")
}

private const val ApplicationName = "GreenQrScanner"
