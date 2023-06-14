package com.seiko.uistate

import androidx.compose.runtime.Immutable

sealed interface UiLoadingState {
    object NotLoading : UiLoadingState
    object Loading : UiLoadingState

    @Immutable
    data class Error(val error: Throwable) : UiLoadingState
}

@Immutable
data class CombinedUiLoadingState(
    val refresh: UiLoadingState,
    val remote: UiLoadingState,
)

internal fun UiLoadingState.toCombined() = CombinedUiLoadingState(
    refresh = this,
    remote = UiLoadingState.NotLoading,
)

fun <T> CombinedUiLoadingState.toUiState(): UiState<T> = refresh.toUiState()

fun <T> UiLoadingState.toUiState(): UiState<T> = when (this) {
    UiLoadingState.NotLoading -> error { "UiLoadingState.NotLoading can't bind to state" }
    UiLoadingState.Loading -> UiState.loading()
    is UiLoadingState.Error -> UiState.failure(error)
}
