package com.seiko.greenqrscanner

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.remember
import com.seiko.greenqrscanner.ui.scene.detail.DetailScene
import com.seiko.greenqrscanner.ui.scene.home.HomeScene
import com.seiko.greenqrscanner.ui.scene.scan.ScanScene
import com.seiko.greenqrscanner.ui.scene.settings.SettingsScene
import com.seiko.greenqrscanner.util.decodeUrl
import com.seiko.greenqrscanner.util.encodeUrl
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition

object Route {
    const val Home = "Home"
    const val Scan = "Scan"
    const val Settings = "Settings"

    object Detail {
        const val paramBarcode = "barcode"
        const val path = "Detail/{$paramBarcode}"
        operator fun invoke(barcode: String) = "Detail/${barcode.encodeUrl()}"
    }

    const val initial = Scan
}

fun RouteBuilder.initRoute(navigator: Navigator) {
    scene(Route.Home) {
        HomeScene(
            navigator = navigator,
        )
    }
    scene(Route.Scan) {
        ScanScene(
            navigator = navigator,
        )
    }
    scene(Route.Settings) {
        SettingsScene()
    }
    scene(Route.Detail.path) {
        DetailScene(
            navigator = navigator,
            barcode = remember {
                it.path<String>(Route.Detail.paramBarcode)!!.decodeUrl()
            },
        )
    }
}

val noneTransition = NavTransition(
    createTransition = EnterTransition.None,
    destroyTransition = ExitTransition.None,
    pauseTransition = ExitTransition.None,
    resumeTransition = EnterTransition.None,
)
