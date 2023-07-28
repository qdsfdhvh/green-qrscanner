package com.seiko.greenqrscanner.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Barcode
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingBag
import androidx.compose.material.icons.rounded.Sms
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material.icons.rounded.TextAd
import androidx.compose.material.icons.rounded.UnknownDocument
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.UiBarcode
import kotlinx.collections.immutable.persistentSetOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarcodeItem(
    item: UiBarcode,
    clickable: BarcodeItemClickable,
    modifier: Modifier = Modifier,
) {
    val state = rememberDismissState(
        confirmValueChange = {
            clickable.onSettingClicked(item)
            false
        },
    )
    SwipeToDismiss(
        state = state,
        directions = remember { persistentSetOf(DismissDirection.EndToStart) },
        background = {
            Box(modifier.fillMaxSize()) {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = "barcode settings",
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .size(24.dp)
                        .align(Alignment.CenterEnd),
                )
            }
        },
        dismissContent = {
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
                            modifier = Modifier.size(24.dp),
                        )
                    },
                    overlineContent = {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(item.title, fontWeight = FontWeight.Bold)

                            val appDateFormatter = LocalAppDateFormatter.current
                            val formattedTime = remember(appDateFormatter, item.time) {
                                appDateFormatter.formatMediumDateTime(item.time)
                            }
                            Text(
                                formattedTime,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    },
                    headlineContent = {
                        Column {
                            BarcodeTypeContent(
                                type = item.type,
                                rawValue = item.rawValue,
                            )
                        }
                    },
                    trailingContent = {
                        IconButton(onClick = { clickable.onStarClicked(item) }) {
                            Image(
                                if (item.isStar) {
                                    Icons.Rounded.Star
                                } else {
                                    Icons.Rounded.StarOutline
                                },
                                contentDescription = "star",
                            )
                        }
                    },
                )
            }
        },
    )
}

@Composable
private fun ColumnScope.BarcodeTypeContent(
    type: BarcodeType,
    rawValue: String,
) {
    when (type) {
        is BarcodeType.Wifi -> {
            Text("ssid: ${type.ssid}")
            Text("password: ${type.password}")
            Text("type: ${type.wifiType.name}")
        }
        is BarcodeType.UrlBookmark -> {
            val uriHandler = LocalUriHandler.current
            Text(
                type.title.ifEmpty { type.url },
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri(type.url)
                },
            )
        }
        is BarcodeType.CalendarEvent -> {
            if (type.summary.isNotEmpty()) {
                Text(type.summary)
            }
            val appDateFormatter = LocalAppDateFormatter.current
            if (type.start != null) {
                val startFormattedTime = remember(appDateFormatter, type.start) {
                    appDateFormatter.formatShortDate(type.start.date) + " " +
                        appDateFormatter.formatShortTime(type.start.time)
                }
                Text("Start: $startFormattedTime")
            }
            if (type.end != null) {
                val endFormattedTime = remember(appDateFormatter, type.end) {
                    appDateFormatter.formatShortDate(type.end.date) + " " +
                        appDateFormatter.formatShortTime(type.end.time)
                }
                Text("End: $endFormattedTime")
            }
        }
        else -> {
            Text(
                text = rawValue,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
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
        BarcodeType.Text -> Icons.Rounded.TextAd
        BarcodeType.ISBN -> Icons.Rounded.Barcode
        BarcodeType.Product -> Icons.Rounded.ShoppingBag
        is BarcodeType.Wifi -> Icons.Rounded.Wifi
        is BarcodeType.UrlBookmark -> Icons.Rounded.Link
        is BarcodeType.Email -> Icons.Rounded.Email
        is BarcodeType.Phone -> Icons.Rounded.Phone
        is BarcodeType.Sms -> Icons.Rounded.Sms
        is BarcodeType.GeoPoint -> Icons.Rounded.MyLocation
        is BarcodeType.ContactInfo -> Icons.Rounded.Contacts
        is BarcodeType.DriverLicense -> Icons.Rounded.DirectionsCar
        is BarcodeType.CalendarEvent -> Icons.Rounded.CalendarMonth
        BarcodeType.Unknown -> Icons.Rounded.UnknownDocument
    }
