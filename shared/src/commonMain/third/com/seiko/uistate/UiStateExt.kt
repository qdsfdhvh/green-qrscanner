package com.seiko.uistate

inline fun <R, T : R> Result<T>.toUiState(
    transform: (data: T) -> R = { it },
): UiState<R> {
    return fold(
        onSuccess = { UiState.success(transform(it)) },
        onFailure = { UiState.failure(it) },
    )
}
