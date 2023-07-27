package com.seiko.greenqrscanner.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.remember
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.ui.scene.add.AddBarcodeScene
import com.seiko.greenqrscanner.ui.scene.add.SelectAddScene
import com.seiko.greenqrscanner.ui.scene.detail.DetailContentScene
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
import moe.tlaster.precompose.navigation.query
import moe.tlaster.precompose.navigation.transition.NavTransition

object Route {
    const val Home = "Home"
    const val Scan = "Scan"
    const val Settings = "Settings"

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

    object DetailFullContent {
        const val path = "DetailFullContent/{$paramBarcode}"
        operator fun invoke(barcode: String) = "DetailFullContent/${barcode.encodeUrl()}"
    }

    const val SelectAdd = "SelectAdd"

    object Add {
        const val path = "Add"
        operator fun invoke(type: AddBarcodeType) = "Add?$paramType=${type.name}"
    }

    const val initial = Scan

    const val paramBarcode = "barcode"
    const val paramType = "type"
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
    scene(Route.DetailFullContent.path) {
        DetailContentScene(
            navigator = navigator,
            barcode = remember {
                it.path<String>(Route.paramBarcode)!!.decodeUrl()
            },
        )
    }
    scene(Route.SelectAdd, navTransition = bottomSheetTransition) {
        SelectAddScene(
            navigator = navigator,
        )
    }
    scene(Route.Add.path) {
        AddBarcodeScene(
            navigator = navigator,
            type = remember {
                it.query<String>(Route.paramType)?.let {
                    AddBarcodeType.valueOf(it)
                } ?: AddBarcodeType.Text
            },
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
