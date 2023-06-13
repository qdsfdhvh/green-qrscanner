package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.ui.icon.rememberBarcode
import com.seiko.greenqrscanner.ui.icon.rememberCalendarMonth
import com.seiko.greenqrscanner.ui.icon.rememberContactMail
import com.seiko.greenqrscanner.ui.icon.rememberContactPhone
import com.seiko.greenqrscanner.ui.icon.rememberContacts
import com.seiko.greenqrscanner.ui.icon.rememberDirectionsCar
import com.seiko.greenqrscanner.ui.icon.rememberLink
import com.seiko.greenqrscanner.ui.icon.rememberMoreVert
import com.seiko.greenqrscanner.ui.icon.rememberMyLocation
import com.seiko.greenqrscanner.ui.icon.rememberShoppingBag
import com.seiko.greenqrscanner.ui.icon.rememberSms
import com.seiko.greenqrscanner.ui.icon.rememberTextAd
import com.seiko.greenqrscanner.ui.icon.rememberUnknownDocument
import com.seiko.greenqrscanner.ui.icon.rememberWifi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarcodeItem(
    item: UiBarcode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(onClick = onClick, modifier = modifier, shape = MaterialTheme.shapes.medium) {
        ListItem(
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
                Icon(
                    rememberMoreVert(),
                    contentDescription = "more setting",
                )
            },
        )
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
