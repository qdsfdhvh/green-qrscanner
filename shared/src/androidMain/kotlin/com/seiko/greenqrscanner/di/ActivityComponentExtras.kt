package com.seiko.greenqrscanner.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.activity.ComponentActivity
import com.moriatsushi.koject.ExperimentalKojectApi
import com.moriatsushi.koject.Singleton
import com.moriatsushi.koject.extras.KojectExtras

@OptIn(ExperimentalKojectApi::class)
internal class ActivityComponentExtras(
    componentActivity: ComponentActivity,
) : KojectExtras {

    @Singleton
    val activity: Activity = componentActivity

    @Singleton
    val context: Context = activity.applicationContext

    @Singleton
    val application: Application = activity.application
}
