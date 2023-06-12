package com.seiko.greenqrscanner.option

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import kotlinx.coroutines.CoroutineDispatcher

@Singleton
@Provides
expect class AppCoroutineDispatcher() {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
