package com.seiko.greenqrscanner.util

import moe.tlaster.precompose.lifecycle.Lifecycle
import moe.tlaster.precompose.lifecycle.LifecycleObserver
import moe.tlaster.precompose.lifecycle.LifecycleOwner

fun LifecycleOwner.toAndroidxLifecycleOwner(): androidx.lifecycle.LifecycleOwner {
    return object : androidx.lifecycle.LifecycleOwner {
        val lifecycleRegistry = androidx.lifecycle.LifecycleRegistry(this)

        init {
            this@toAndroidxLifecycleOwner.lifecycle.addObserver(
                object : LifecycleObserver {
                    var observer: LifecycleObserver = this
                    override fun onStateChanged(state: Lifecycle.State) {
                        when (state) {
                            Lifecycle.State.Initialized -> lifecycleRegistry.handleLifecycleEvent(
                                androidx.lifecycle.Lifecycle.Event.ON_CREATE,
                            )
                            Lifecycle.State.Active -> lifecycleRegistry.handleLifecycleEvent(
                                androidx.lifecycle.Lifecycle.Event.ON_RESUME,
                            )
                            Lifecycle.State.InActive -> lifecycleRegistry.handleLifecycleEvent(
                                androidx.lifecycle.Lifecycle.Event.ON_PAUSE,
                            )
                            Lifecycle.State.Destroyed -> {
                                lifecycleRegistry.handleLifecycleEvent(androidx.lifecycle.Lifecycle.Event.ON_DESTROY)
                                this@toAndroidxLifecycleOwner.lifecycle.removeObserver(observer)
                            }
                        }
                    }
                },
            )
        }

        override val lifecycle: androidx.lifecycle.Lifecycle
            get() = lifecycleRegistry
    }
}
