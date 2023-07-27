package com.seiko.greenqrscanner.util.storage

import okio.Path

expect class StorageService {
    val appDir: Path
    val cacheDir: Path
}
