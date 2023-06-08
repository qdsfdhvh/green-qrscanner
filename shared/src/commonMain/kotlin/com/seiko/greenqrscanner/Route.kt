package com.seiko.greenqrscanner

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.seiko.greenqrscanner.ui.scene.detail.DetailScene
import com.seiko.greenqrscanner.ui.scene.home.HomeScene
import com.seiko.greenqrscanner.ui.scene.scan.ScanScene
import com.seiko.greenqrscanner.ui.scene.settings.SettingsScene
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path
import moe.tlaster.precompose.navigation.transition.NavTransition

object Route {
    const val Home = "Home"
    const val Scan = "Scan"
    const val Settings = "Settings"

    object Detail {
        const val paramQrCode = "qrCode"
        const val path = "Detail/{$paramQrCode}"
        operator fun invoke(qrCode: String) = "Detail/$qrCode"
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
            qrCode = it.path(Route.Detail.paramQrCode)!!,
        )
    }
}

val noneTransition = NavTransition(
    createTransition = EnterTransition.None,
    destroyTransition = ExitTransition.None,
    pauseTransition = ExitTransition.None,
    resumeTransition = EnterTransition.None,
)
