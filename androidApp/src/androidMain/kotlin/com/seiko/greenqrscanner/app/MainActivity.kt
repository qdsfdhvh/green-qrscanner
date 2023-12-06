package com.seiko.greenqrscanner.app

import MainView
import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import com.seiko.greenqrscanner.di.startKoject
import com.seiko.greenqrscanner.di.stopKoject
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.Navigator

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoject(this)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.TRANSPARENT
        window.navigationBarColor = Color.TRANSPARENT
        setContent {
            PreComposeApp {
                MainView(viewModel.navigator)
            }
        }
    }

    override fun onDestroy() {
        stopKoject()
        super.onDestroy()
    }
}

class MainViewModel : ViewModel() {
    val navigator = Navigator()
}
