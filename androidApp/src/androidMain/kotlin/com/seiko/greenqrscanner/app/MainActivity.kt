package com.seiko.greenqrscanner.app

import MainView
import android.graphics.Color
import android.os.Bundle
import androidx.core.view.WindowCompat
import com.seiko.greenqrscanner.di.startKoject
import com.seiko.greenqrscanner.di.stopKoject
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent

class MainActivity : PreComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoject(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContent {
            MainView()
        }
    }

    override fun onDestroy() {
        stopKoject()
        super.onDestroy()
    }
}
