package com.seiko.greenqrscanner

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.seiko.greenqrscanner.ui.scene.detail.DetailScene
import com.seiko.greenqrscanner.ui.scene.home.HomeScene
import com.seiko.greenqrscanner.ui.scene.scan.ScanScene
import com.seiko.greenqrscanner.ui.scene.settings.SettingsScene
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.transition.NavTransition

object Route {
    const val Home = "Home"
    const val Scan = "Scan"
    const val Settings = "Settings"
    const val Detail = "Detail"

    const val initial = Scan
}

fun RouteBuilder.initRoute(navigator: Navigator) {
    scene(Route.Home, navTransition = noneTransition) {
        HomeScene(
            navigator = navigator,
        )
    }
    scene(Route.Scan, navTransition = noneTransition) {
        ScanScene()
    }
    scene(Route.Settings, navTransition = noneTransition) {
        SettingsScene()
    }
    scene(Route.Detail) {
        DetailScene(
            navigator = navigator,
        )
    }
}


private val noneTransition = NavTransition(
    createTransition = EnterTransition.None,
    destroyTransition = ExitTransition.None,
    pauseTransition = ExitTransition.None,
    resumeTransition = EnterTransition.None,
)
