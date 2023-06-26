package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction

val NextKeyboardOptions by lazy(LazyThreadSafetyMode.NONE) {
    KeyboardOptions(
        imeAction = ImeAction.Next,
    )
}

val DoneKeyboardOptions by lazy(LazyThreadSafetyMode.NONE) {
    KeyboardOptions(
        imeAction = ImeAction.Done,
    )
}
