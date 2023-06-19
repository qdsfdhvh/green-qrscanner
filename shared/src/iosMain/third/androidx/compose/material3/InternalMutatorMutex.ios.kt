package androidx.compose.material3

import kotlinx.atomicfu.atomic

actual class InternalAtomicReference<V> actual constructor(value: V) {

    private val wrapper = atomic(value)

    actual fun get(): V {
        return wrapper.value
    }

    actual fun set(value: V) {
        wrapper.value = value
    }

    actual fun getAndSet(value: V): V {
        return wrapper.getAndSet(value)
    }

    actual fun compareAndSet(expect: V, newValue: V): Boolean {
        return wrapper.compareAndSet(expect, newValue)
    }
}
