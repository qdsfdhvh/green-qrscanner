package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FixedThreshold
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.ui.icon.rememberBarcode
import com.seiko.greenqrscanner.ui.icon.rememberCalendarMonth
import com.seiko.greenqrscanner.ui.icon.rememberContactMail
import com.seiko.greenqrscanner.ui.icon.rememberContactPhone
import com.seiko.greenqrscanner.ui.icon.rememberContacts
import com.seiko.greenqrscanner.ui.icon.rememberDirectionsCar
import com.seiko.greenqrscanner.ui.icon.rememberLink
import com.seiko.greenqrscanner.ui.icon.rememberMyLocation
import com.seiko.greenqrscanner.ui.icon.rememberSettings
import com.seiko.greenqrscanner.ui.icon.rememberShoppingBag
import com.seiko.greenqrscanner.ui.icon.rememberSms
import com.seiko.greenqrscanner.ui.icon.rememberStar
import com.seiko.greenqrscanner.ui.icon.rememberStarFilled
import com.seiko.greenqrscanner.ui.icon.rememberTextAd
import com.seiko.greenqrscanner.ui.icon.rememberUnknownDocument
import com.seiko.greenqrscanner.ui.icon.rememberWifi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarcodeItem(
    item: UiBarcode,
    clickable: BarcodeItemClickable,
    modifier: Modifier = Modifier,
) {
    val state = rememberDismissState {
        clickable.onSettingClicked(item)
        false
    }
    SwipeToDismiss(
        state = state,
        directions = remember { mutableSetOf(DismissDirection.EndToStart) },
        dismissThresholds = {
            FixedThreshold(40.dp)
        },
        background = {
            Box(modifier.fillMaxSize()) {
                Icon(
                    rememberSettings(),
                    contentDescription = "barcode settings",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .align(Alignment.CenterEnd),
                )
            }
        },
    ) {
        Surface(
            onClick = { clickable.onItemClicked(item) },
            shape = MaterialTheme.shapes.medium,
            // tonalElevation = 2.dp,
            // color = MaterialTheme.colorScheme.primary,
            modifier = modifier,
        ) {
            ListItem(
                colors = ListItemDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                leadingContent = {
                    Icon(
                        item.type.icon,
                        contentDescription = item.title,
                    )
                },
                headlineText = {
                    Text(item.title)
                },
                supportingText = {
                    BarcodeTypeContent(
                        type = item.type,
                        rawValue = item.rawValue,
                    )
                },
                trailingContent = {
                    IconButton(onClick = { clickable.onStarClicked(item) }) {
                        Image(
                            if (item.isStar) {
                                rememberStarFilled()
                            } else {
                                rememberStar()
                            },
                            contentDescription = "star",
                        )
                    }
                    // Icon(
                    //     rememberMoreVert(),
                    //     contentDescription = "more setting",
                    // )
                },
            )
        }
    }
}

@Composable
private fun BarcodeTypeContent(
    type: BarcodeType,
    rawValue: String,
) {
    when (type) {
        BarcodeType.Text,
        BarcodeType.ISBN,
        BarcodeType.Product -> {
            Text(rawValue)
        }
        is BarcodeType.Wifi -> {
            Column {
                Text("ssid: ${type.ssid}")
                Text("password: ${type.password}")
                Text("type: ${type.wifiType.name}")
            }
        }
        is BarcodeType.UrlBookmark -> {
            Column {
                val uriHandler = LocalUriHandler.current
                Text(
                    type.url,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        uriHandler.openUri(type.url)
                    },
                )
                if (type.title.isNotEmpty()) {
                    Text(type.title)
                }
            }
        }
        else -> {
            Text(rawValue)
        }
    }
}

data class BarcodeItemClickable(
    val onItemClicked: (UiBarcode) -> Unit,
    val onStarClicked: (UiBarcode) -> Unit,
    val onSettingClicked: (UiBarcode) -> Unit,
)

private val BarcodeType.icon: ImageVector
    @Composable
    get() = when (this) {
        BarcodeType.Text -> rememberTextAd()
        BarcodeType.ISBN -> rememberBarcode()
        BarcodeType.Product -> rememberShoppingBag()
        is BarcodeType.Wifi -> rememberWifi()
        is BarcodeType.UrlBookmark -> rememberLink()
        is BarcodeType.Email -> rememberContactMail()
        is BarcodeType.Phone -> rememberContactPhone()
        is BarcodeType.Sms -> rememberSms()
        is BarcodeType.GeoPoint -> rememberMyLocation()
        is BarcodeType.ContactInfo -> rememberContacts()
        is BarcodeType.DriverLicense -> rememberDirectionsCar()
        is BarcodeType.CalendarEvent -> rememberCalendarMonth()
        BarcodeType.Unknown -> rememberUnknownDocument()
    }
