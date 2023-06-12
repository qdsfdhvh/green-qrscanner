package com.seiko.greenqrscanner.data.mapper

import androidx.compose.ui.graphics.vector.ImageVector
import app.com.seiko.greenqrscanner.DbBarcode
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.UiBarcode
import compose.icons.TablerIcons
import compose.icons.tablericons.CalendarEvent
import compose.icons.tablericons.Clock
import compose.icons.tablericons.FileText
import compose.icons.tablericons.License
import compose.icons.tablericons.Link
import compose.icons.tablericons.Phone
import compose.icons.tablericons.Send
import compose.icons.tablericons.ShoppingCart
import compose.icons.tablericons.Wifi

fun DbBarcode.toUi() = UiBarcode(
    rawValue = raw_value,
    // time = Instant.fromEpochMilliseconds(update_time).toString(),
    type = type,
    icon = type.icon,
    title = type.title,
)

private val BarcodeType.icon: ImageVector
    get() = when (this) {
        BarcodeType.Text -> TablerIcons.FileText
        BarcodeType.ISBN -> TablerIcons.FileText // TODO
        BarcodeType.Product -> TablerIcons.ShoppingCart
        is BarcodeType.Wifi -> TablerIcons.Wifi
        is BarcodeType.UrlBookmark -> TablerIcons.Link
        is BarcodeType.Email -> TablerIcons.Send
        is BarcodeType.Phone -> TablerIcons.Phone
        is BarcodeType.Sms -> TablerIcons.Clock // TODO
        is BarcodeType.GeoPoint -> TablerIcons.Clock // TODO
        is BarcodeType.ContactInfo -> TablerIcons.Clock // TODO
        is BarcodeType.DriverLicense -> TablerIcons.License
        is BarcodeType.CalendarEvent -> TablerIcons.CalendarEvent
        BarcodeType.Unknown -> TablerIcons.Clock // TODO
    }
private val BarcodeType.title: String
    get() = when (this) {
        BarcodeType.Text -> "Text"
        BarcodeType.ISBN -> "ISBN"
        BarcodeType.Product -> "Product"
        is BarcodeType.Wifi -> "Wifi"
        is BarcodeType.UrlBookmark -> "Url"
        is BarcodeType.Email -> "Email"
        is BarcodeType.Phone -> "Phone"
        is BarcodeType.Sms -> "SMS"
        is BarcodeType.GeoPoint -> "GEO"
        is BarcodeType.ContactInfo -> "Contact Info"
        is BarcodeType.DriverLicense -> "Driver License"
        is BarcodeType.CalendarEvent -> "Calendar Event"
        BarcodeType.Unknown -> "Unknown"
    }
