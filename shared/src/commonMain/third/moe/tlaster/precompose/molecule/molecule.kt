package moe.tlaster.precompose.molecule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.remember
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import moe.tlaster.precompose.stateholder.LocalStateHolder
import kotlin.coroutines.CoroutineContext

internal expect fun providePlatformDispatcher(): CoroutineContext

@OptIn(ExperimentalStdlibApi::class)
private class PresenterHolder<T>(
    body: @Composable () -> T,
    useImmediateClock: Boolean = false,
) : AutoCloseable {
    private val dispatcher = providePlatformDispatcher()
    private val clock = if (useImmediateClock || dispatcher[MonotonicFrameClock] == null) {
        RecompositionMode.Immediate
    } else {
        RecompositionMode.ContextClock
    }
    private val scope = CoroutineScope(dispatcher)
    val state = scope.launchMolecule(mode = clock, body = body)
    override fun close() {
        scope.cancel()
    }
}

@Composable
fun <T> producePresenter(
    useImmediateClock: Boolean = false,
    body: @Composable () -> T,
): State<T> {
    val keyHash = currentCompositeKeyHash
    val key = remember {
        PresenterHolder::class.simpleName + "@" + keyHash.toString(36)
    }
    val stateHolder = LocalStateHolder.current
    return stateHolder.getOrPut(key) {
        PresenterHolder(body, useImmediateClock)
    }.state.collectAsState()
}
