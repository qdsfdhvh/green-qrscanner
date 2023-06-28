package androidx.compose.material3

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
internal actual val defaultTimePickerLayoutType: TimePickerLayoutType =
    TimePickerLayoutType.Vertical

internal actual val is24HourFormat: Boolean
    @Composable
    @ReadOnlyComposable get() = false

@Composable
internal actual fun touchExplorationState(): State<Boolean> {
    // TODO ios touch exploration
    return remember { derivedStateOf { false } }
}
