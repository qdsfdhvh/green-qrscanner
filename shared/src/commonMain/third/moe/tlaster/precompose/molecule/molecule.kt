package moe.tlaster.precompose.molecule

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.currentCompositeKeyHash
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.molecule.RecompositionMode
import app.cash.molecule.launchMolecule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import moe.tlaster.precompose.stateholder.LocalStateHolder
import kotlin.coroutines.CoroutineContext

internal expect fun providePlatformDispatcher(): CoroutineContext

@OptIn(ExperimentalStdlibApi::class)
class PresenterHolder<T>(
    body: @Composable () -> T,
    useImmediateClock: Boolean = false,
) : AutoCloseable {

    private val scope: CoroutineScope

    private var _state: MutableState<T>? = null
    val state: State<T> get() = _state!!

    init {
        val dispatcher = providePlatformDispatcher()
        scope = CoroutineScope(dispatcher)
        scope.launchMolecule(
            mode = if (useImmediateClock || dispatcher[MonotonicFrameClock] == null) {
                RecompositionMode.Immediate
            } else {
                RecompositionMode.ContextClock
            },
            body = body,
            emitter = { value ->
                val outputState = _state
                if (outputState != null) {
                    outputState.value = value
                } else {
                    _state = mutableStateOf(value)
                }
            },
        )
    }

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
    return LocalStateHolder.current.getOrPut(key) {
        PresenterHolder(body, useImmediateClock)
    }.state
}
