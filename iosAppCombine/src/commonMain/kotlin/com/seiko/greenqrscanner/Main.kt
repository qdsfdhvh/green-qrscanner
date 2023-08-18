package com.seiko.greenqrscanner

import MainComposeViewController
import com.seiko.greenqrscanner.di.startKoject
import platform.UIKit.UIViewController

fun initDI() {
    startKoject()
}

@Suppress("FunctionName")
fun MainViewController(): UIViewController = MainComposeViewController()
