package com.seiko.greenqrscanner.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Contacts
import androidx.compose.material.icons.rounded.DirectionsCar
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.MyLocation
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Sms
import androidx.compose.material.icons.rounded.TextAd
import androidx.compose.material.icons.rounded.UnknownDocument
import androidx.compose.material.icons.rounded.Wifi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.vector.ImageVector
import com.seiko.greenqrscanner.MR
import com.seiko.greenqrscanner.util.stringResource
import com.seiko.greenqrscanner.util.strings

enum class AddBarcodeType {
    Text,

    // ISBN,
    // Product,
    Url,
    Wifi,
    Email,
    Phone,
    Sms,
    Geo,
    ContactInfo,
    DriverLicense,
    CalendarEvent,
    Unknown
}

val AddBarcodeType.icon: ImageVector
    get() = when (this) {
        AddBarcodeType.Text -> Icons.Rounded.TextAd
        // AddBarcodeType.ISBN -> Icons.Rounded.Barcode
        // AddBarcodeType.Product -> Icons.Rounded.ShoppingBag
        AddBarcodeType.Url -> Icons.Rounded.Link
        AddBarcodeType.Wifi -> Icons.Rounded.Wifi
        AddBarcodeType.Email -> Icons.Rounded.Email
        AddBarcodeType.Phone -> Icons.Rounded.Phone
        AddBarcodeType.Sms -> Icons.Rounded.Sms
        AddBarcodeType.Geo -> Icons.Rounded.MyLocation
        AddBarcodeType.ContactInfo -> Icons.Rounded.Contacts
        AddBarcodeType.DriverLicense -> Icons.Rounded.DirectionsCar
        AddBarcodeType.CalendarEvent -> Icons.Rounded.CalendarMonth
        AddBarcodeType.Unknown -> Icons.Rounded.UnknownDocument
    }

val AddBarcodeType.title: String
    @Composable
    @ReadOnlyComposable
    get() = when (this) {
        AddBarcodeType.Text -> stringResource(MR.strings.text)
        // AddBarcodeType.ISBN -> ""
        // AddBarcodeType.Product -> ""
        AddBarcodeType.Url -> stringResource(MR.strings.url)
        AddBarcodeType.Wifi -> stringResource(MR.strings.wifi)
        AddBarcodeType.Email -> stringResource(MR.strings.email)
        AddBarcodeType.Phone -> stringResource(MR.strings.phone)
        AddBarcodeType.Sms -> stringResource(MR.strings.sms)
        AddBarcodeType.Geo -> stringResource(MR.strings.geo)
        AddBarcodeType.ContactInfo -> stringResource(MR.strings.contract_info)
        AddBarcodeType.DriverLicense -> stringResource(MR.strings.driver_license)
        AddBarcodeType.CalendarEvent -> stringResource(MR.strings.calendar_event)
        AddBarcodeType.Unknown -> stringResource(MR.strings.unknown)
    }
