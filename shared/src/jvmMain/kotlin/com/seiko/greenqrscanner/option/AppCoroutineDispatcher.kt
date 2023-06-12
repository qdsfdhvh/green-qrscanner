package com.seiko.greenqrscanner.option

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class AppCoroutineDispatcher actual constructor() {
    actual val main: CoroutineDispatcher get() = Dispatchers.Main
    actual val io: CoroutineDispatcher get() = Dispatchers.IO
    actual val default: CoroutineDispatcher get() = Dispatchers.Default
}
