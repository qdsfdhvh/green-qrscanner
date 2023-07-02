package moe.tlaster.precompose.molecule

import androidx.compose.runtime.DefaultMonotonicFrameClock
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

@Suppress("DEPRECATION")
internal actual fun providePlatformDispatcher(): CoroutineContext =
    DefaultMonotonicFrameClock + Dispatchers.Main
