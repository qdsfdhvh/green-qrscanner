package com.seiko.greenqrscanner.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.remember
import com.seiko.greenqrscanner.ui.scene.add.AddBottomSheet
import com.seiko.greenqrscanner.ui.scene.detail.DetailScene
import com.seiko.greenqrscanner.ui.scene.home.HomeScene
import com.seiko.greenqrscanner.ui.scene.popup.BarcodeSettingsBottomSheet
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
    const val Add = "Add"

    object Popup {
        object BarcodeSettings {
            const val path = "Popup/BarcodeSettings/$paramBarcode"
            operator fun invoke(barcode: String) = "Popup/BarcodeSettings/${barcode.encodeUrl()}"
        }
    }

    object Detail {
        const val path = "Detail/{$paramBarcode}"
        operator fun invoke(barcode: String) = "Detail/${barcode.encodeUrl()}"
    }

    const val initial = Scan

    const val paramBarcode = "barcode"
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
                it.path<String>(Route.paramBarcode)!!.decodeUrl()
            },
        )
    }
    floating(Route.Add) {
        AddBottomSheet(
            navigator = navigator,
        )
    }
    floating(Route.Popup.BarcodeSettings.path) {
        BarcodeSettingsBottomSheet(
            navigator = navigator,
            barcode = remember {
                it.path<String>(Route.paramBarcode)!!.decodeUrl()
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

val bottomSheetTransition = NavTransition(
    createTransition = fadeIn(),
    destroyTransition = fadeOut(),
    pauseTransition = fadeOut(),
    resumeTransition = fadeIn(),
)
