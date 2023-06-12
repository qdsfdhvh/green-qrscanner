package moe.tlaster.precompose.molecule

import app.cash.molecule.AndroidUiDispatcher
import kotlin.coroutines.CoroutineContext

internal actual fun providePlatformDispatcher(): CoroutineContext = AndroidUiDispatcher.Main
