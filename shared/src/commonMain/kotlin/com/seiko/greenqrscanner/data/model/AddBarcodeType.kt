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
import androidx.compose.ui.graphics.vector.ImageVector
import com.seiko.greenqrscanner.MR
import com.seiko.greenqrscanner.util.stringResource

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
    get() = stringResource(
        when (this) {
            AddBarcodeType.Text -> MR.strings.text
            // AddBarcodeType.ISBN -> ""
            // AddBarcodeType.Product -> ""
            AddBarcodeType.Url -> MR.strings.url
            AddBarcodeType.Wifi -> MR.strings.wifi
            AddBarcodeType.Email -> MR.strings.email
            AddBarcodeType.Phone -> MR.strings.phone
            AddBarcodeType.Sms -> MR.strings.sms
            AddBarcodeType.Geo -> MR.strings.geo
            AddBarcodeType.ContactInfo -> MR.strings.contract_info
            AddBarcodeType.DriverLicense -> MR.strings.driver_license
            AddBarcodeType.CalendarEvent -> MR.strings.calendar_event
            AddBarcodeType.Unknown -> MR.strings.unknown
        },
    )
