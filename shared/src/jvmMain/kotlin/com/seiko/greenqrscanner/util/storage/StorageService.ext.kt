package com.seiko.greenqrscanner.util.storage

import okio.Path

val StorageService.databaseDir: Path
    get() = appDir.resolve("database")

val StorageService.preferenceDir: Path
    get() = appDir.resolve("preference")
