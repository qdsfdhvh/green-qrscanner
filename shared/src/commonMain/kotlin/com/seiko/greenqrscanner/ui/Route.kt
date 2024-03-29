package com.seiko.greenqrscanner.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.util.encodeUrl
import moe.tlaster.precompose.navigation.transition.NavTransition

object Route {
    const val Home = "Home"
    const val Scan = "Scan"
    const val Settings = "Settings"
    const val AboutLibraries = "AboutLibraries"

    object Popup {
        object BarcodeSettings {
            const val path = "Popup/BarcodeSettings/{$paramBarcode}"
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

val noneTransition = NavTransition(
    createTransition = EnterTransition.None,
    destroyTransition = ExitTransition.None,
    pauseTransition = ExitTransition.None,
    resumeTransition = EnterTransition.None,
)
