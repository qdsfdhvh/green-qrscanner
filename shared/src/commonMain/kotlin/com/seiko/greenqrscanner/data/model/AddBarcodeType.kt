package com.seiko.greenqrscanner.data.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.seiko.greenqrscanner.ui.icon.rememberBarcode
import com.seiko.greenqrscanner.ui.icon.rememberCalendarMonth
import com.seiko.greenqrscanner.ui.icon.rememberContactMail
import com.seiko.greenqrscanner.ui.icon.rememberContactPhone
import com.seiko.greenqrscanner.ui.icon.rememberContacts
import com.seiko.greenqrscanner.ui.icon.rememberDirectionsCar
import com.seiko.greenqrscanner.ui.icon.rememberLink
import com.seiko.greenqrscanner.ui.icon.rememberMyLocation
import com.seiko.greenqrscanner.ui.icon.rememberShoppingBag
import com.seiko.greenqrscanner.ui.icon.rememberSms
import com.seiko.greenqrscanner.ui.icon.rememberTextAd
import com.seiko.greenqrscanner.ui.icon.rememberUnknownDocument
import com.seiko.greenqrscanner.ui.icon.rememberWifi

enum class AddBarcodeType {
    Text,
    ISBN,
    Product,
    Wifi,
    Url,
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
    @Composable
    get() = when (this) {
        AddBarcodeType.Text -> rememberTextAd()
        AddBarcodeType.ISBN -> rememberBarcode()
        AddBarcodeType.Product -> rememberShoppingBag()
        AddBarcodeType.Wifi -> rememberWifi()
        AddBarcodeType.Url -> rememberLink()
        AddBarcodeType.Email -> rememberContactMail()
        AddBarcodeType.Phone -> rememberContactPhone()
        AddBarcodeType.Sms -> rememberSms()
        AddBarcodeType.Geo -> rememberMyLocation()
        AddBarcodeType.ContactInfo -> rememberContacts()
        AddBarcodeType.DriverLicense -> rememberDirectionsCar()
        AddBarcodeType.CalendarEvent -> rememberCalendarMonth()
        AddBarcodeType.Unknown -> rememberUnknownDocument()
    }
