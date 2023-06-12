package com.seiko.uistate

import androidx.compose.runtime.Immutable

@Immutable
data class UiState<out T>(
    val loadingState: CombinedUiLoadingState,
    val data: T?,
) {
    val isLoading: Boolean
        get() = loadingState.refresh === UiLoadingState.Loading ||
            loadingState.refresh === UiLoadingState.Loading

    val isFailure: Boolean
        get() = loadingState.refresh is UiLoadingState.Error && data == null

    inline val isSuccess: Boolean
        get() = data != null

    companion object {
        fun <T> loading(): UiState<T> {
            return UiState(
                loadingState = UiLoadingState.Loading.toCombined(),
                data = null,
            )
        }

        fun <T> success(data: T): UiState<T> {
            return UiState(
                loadingState = UiLoadingState.NotLoading.toCombined(),
                data = data,
            )
        }

        fun <T> failure(error: Throwable): UiState<T> {
            return UiState(
                loadingState = UiLoadingState.Error(error).toCombined(),
                data = null,
            )
        }
    }
}

inline fun <R, T : R> UiState<T>.getOrElse(
    action: (loadingState: CombinedUiLoadingState) -> R,
): R {
    return when {
        data != null -> data
        else -> action(loadingState)
    }
}

inline fun <R, T> UiState<T>.fold(
    onSuccess: (data: T) -> R,
    onOthers: (loadingState: CombinedUiLoadingState) -> R,
): R {
    return when {
        data != null -> onSuccess(data)
        else -> onOthers(loadingState)
    }
}

inline fun <R, T> UiState<T>.map(transform: (data: T) -> R): UiState<R> {
    return UiState(
        loadingState = loadingState,
        data = data?.let(transform),
    )
}

inline fun <R, T> UiState<T>.flatMap(transform: (data: T) -> UiState<R>): UiState<R> {
    return if (data != null) {
        transform(data)
    } else {
        UiState(
            loadingState = loadingState,
            data = null,
        )
    }
}

inline fun <T> UiState<T>.onLoading(action: () -> Unit): UiState<T> {
    if (isLoading) action()
    return this
}

inline fun <T> UiState<T>.onFailure(action: (exception: Throwable) -> Unit): UiState<T> {
    if (isFailure) action((loadingState.refresh as UiLoadingState.Error).error)
    return this
}

inline fun <T> UiState<T>.onSuccess(action: (data: T) -> Unit): UiState<T> {
    if (data != null) action(data)
    return this
}
