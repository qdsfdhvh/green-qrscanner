package com.seiko.greenqrscanner.di

import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Koject
import com.moriatsushi.koject.start

@OptIn(ExperimentalKojectApi::class)
fun startKoject(
    componentActivity: ComponentActivity,
) {
    Koject.start {
        addExtras(ActivityComponentExtras(componentActivity))
    }
}

fun stopKoject() {
    Koject.stop()
}
