package androidx.compose.material3

import android.content.Context
import android.text.format.DateFormat.is24HourFormat
import android.view.accessibility.AccessibilityManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@OptIn(ExperimentalMaterial3Api::class)
internal actual val defaultTimePickerLayoutType: TimePickerLayoutType
    @Composable
    @ReadOnlyComposable get() = with(LocalConfiguration.current) {
        if (screenHeightDp < screenWidthDp) {
            TimePickerLayoutType.Horizontal
        } else {
            TimePickerLayoutType.Vertical
        }
    }

internal actual val is24HourFormat: Boolean
    @Composable
    @ReadOnlyComposable get() = is24HourFormat(LocalContext.current)

@Composable
internal actual fun touchExplorationState(): State<Boolean> {
    val context = LocalContext.current
    val accessibilityManager = remember {
        context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
    }

    val listener = remember { Listener() }

    LocalLifecycleOwner.current.lifecycle.ObserveState(
        handleEvent = { event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                listener.register(accessibilityManager)
            }
        },
        onDispose = {
            listener.unregister(accessibilityManager)
        },
    )

    return remember { derivedStateOf { listener.isEnabled() } }
}

@Composable
private fun Lifecycle.ObserveState(
    handleEvent: (Lifecycle.Event) -> Unit = {},
    onDispose: () -> Unit = {}
) {
    DisposableEffect(this) {
        val observer = LifecycleEventObserver { _, event ->
            handleEvent(event)
        }
        this@ObserveState.addObserver(observer)
        onDispose {
            onDispose()
            this@ObserveState.removeObserver(observer)
        }
    }
}

private class Listener :
    AccessibilityManager.AccessibilityStateChangeListener,
    AccessibilityManager.TouchExplorationStateChangeListener {
    private var accessibilityEnabled by mutableStateOf(false)
    private var touchExplorationEnabled by mutableStateOf(false)

    fun isEnabled() = accessibilityEnabled && touchExplorationEnabled

    override fun onAccessibilityStateChanged(it: Boolean) {
        accessibilityEnabled = it
    }

    override fun onTouchExplorationStateChanged(it: Boolean) {
        touchExplorationEnabled = it
    }

    fun register(am: AccessibilityManager) {
        accessibilityEnabled = am.isEnabled
        touchExplorationEnabled = am.isTouchExplorationEnabled

        am.addTouchExplorationStateChangeListener(this)
        am.addAccessibilityStateChangeListener(this)
    }

    fun unregister(am: AccessibilityManager) {
        am.removeTouchExplorationStateChangeListener(this)
        am.removeAccessibilityStateChangeListener(this)
    }
}
