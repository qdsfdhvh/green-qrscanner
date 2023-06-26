package com.seiko.flow

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Immutable
interface ImmutableFlow<T> : Flow<T>

fun <T> Flow<T>.immutable() = object : ImmutableFlow<T> {
    override suspend fun collect(collector: FlowCollector<T>) {
        this@immutable.collect(collector)
    }
}

@Immutable
interface ImmutableSharedFlow<T> : ImmutableFlow<T>, SharedFlow<T>

fun <T> SharedFlow<T>.immutable() = object : ImmutableSharedFlow<T> {
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        this@immutable.collect(collector)
    }

    override val replayCache: List<T>
        get() = this@immutable.replayCache
}

@Immutable
interface ImmutableStateFlow<T> : ImmutableFlow<T>, StateFlow<T>

fun <T> StateFlow<T>.immutable() = object : ImmutableStateFlow<T> {
    override suspend fun collect(collector: FlowCollector<T>): Nothing {
        this@immutable.collect(collector)
    }

    override val replayCache: List<T>
        get() = this@immutable.replayCache

    override val value: T
        get() = this@immutable.value
}
