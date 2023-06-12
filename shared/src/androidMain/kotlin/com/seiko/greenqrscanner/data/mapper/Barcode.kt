package com.seiko.greenqrscanner.data.mapper

import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.model.BarcodeFormat
import com.seiko.greenqrscanner.data.model.BarcodeType

typealias MlkitBarcode = com.google.mlkit.vision.barcode.common.Barcode
typealias MlkitBarcodeWifi = com.google.mlkit.vision.barcode.common.Barcode.WiFi
typealias MlkitBarcodePhone = com.google.mlkit.vision.barcode.common.Barcode.Phone
typealias MlkitBarcodeEmail = com.google.mlkit.vision.barcode.common.Barcode.Email
typealias MlkitBarcodeAddress = com.google.mlkit.vision.barcode.common.Barcode.Address
typealias MlkitBarcodeContactInfo = com.google.mlkit.vision.barcode.common.Barcode.ContactInfo
typealias MlkitBarcodeDriverLicense = com.google.mlkit.vision.barcode.common.Barcode.DriverLicense
typealias MlkitBarcodeCalendarEvent = com.google.mlkit.vision.barcode.common.Barcode.CalendarEvent
typealias MlkitBarcodeCalendarDateTime = com.google.mlkit.vision.barcode.common.Barcode.CalendarDateTime

fun List<MlkitBarcode>.toBarcode(): List<Barcode> {
    return map { it.toBarcode() }
}

fun MlkitBarcode.toBarcode(): Barcode {
    return Barcode(
        rawValue = rawValue.orEmpty(),
        format = toFormat(),
        type = toType(),
    )
}

private fun MlkitBarcode.toFormat(): BarcodeFormat {
    return when (format) {
        MlkitBarcode.FORMAT_QR_CODE,
        MlkitBarcode.FORMAT_AZTEC,
        MlkitBarcode.FORMAT_PDF417,
        MlkitBarcode.FORMAT_DATA_MATRIX -> BarcodeFormat.FORMAT_2D
        MlkitBarcode.FORMAT_CODABAR,
        MlkitBarcode.FORMAT_CODE_39,
        MlkitBarcode.FORMAT_CODE_93,
        MlkitBarcode.FORMAT_CODE_128,
        MlkitBarcode.FORMAT_EAN_8,
        MlkitBarcode.FORMAT_EAN_13,
        MlkitBarcode.FORMAT_UPC_A,
        MlkitBarcode.FORMAT_UPC_E,
        MlkitBarcode.FORMAT_ITF -> BarcodeFormat.FORMAT_1D
        else -> BarcodeFormat.UNKNOWN
    }
}

private fun MlkitBarcode.toType(): BarcodeType? {
    return when (valueType) {
        MlkitBarcode.TYPE_TEXT -> BarcodeType.Text
        MlkitBarcode.TYPE_WIFI -> BarcodeType.Wifi(
            ssid = wifi?.ssid.orEmpty(),
            password = wifi?.password.orEmpty(),
            wifiType = when (wifi?.encryptionType) {
                MlkitBarcodeWifi.TYPE_WPA -> BarcodeType.Wifi.Type.WPA
                MlkitBarcodeWifi.TYPE_WEP -> BarcodeType.Wifi.Type.WEP
                else -> BarcodeType.Wifi.Type.OPEN
            },
        )
        MlkitBarcode.TYPE_URL -> url?.let {
            BarcodeType.UrlBookmark(
                title = it.title.orEmpty(),
                url = it.url.orEmpty(),
            )
        }
        MlkitBarcode.TYPE_SMS -> sms?.let {
            BarcodeType.Sms(
                message = it.message.orEmpty(),
                phoneNumber = it.phoneNumber.orEmpty(),
            )
        }
        MlkitBarcode.TYPE_GEO -> geoPoint?.let {
            BarcodeType.GeoPoint(
                lat = it.lat,
                lng = it.lng,
            )
        }
        MlkitBarcode.TYPE_CONTACT_INFO -> contactInfo?.toCommon()
        MlkitBarcode.TYPE_PHONE -> phone?.toCommon()
        MlkitBarcode.TYPE_EMAIL -> email?.toCommon()
        MlkitBarcode.TYPE_DRIVER_LICENSE -> driverLicense?.toCommon()
        MlkitBarcode.TYPE_CALENDAR_EVENT -> calendarEvent?.toCommon()
        MlkitBarcode.TYPE_ISBN -> BarcodeType.ISBN
        MlkitBarcode.TYPE_PRODUCT -> BarcodeType.Product
        else -> null
    }
}

private fun MlkitBarcodeContactInfo.toCommon(): BarcodeType.ContactInfo {
    return BarcodeType.ContactInfo(
        name = name?.let {
            BarcodeType.PersonName(
                formattedName = it.formattedName,
                pronunciation = it.pronunciation,
                prefix = it.prefix,
                first = it.first,
                middle = it.middle,
                last = it.last,
                suffix = it.suffix,
            )
        },
        organization = organization.orEmpty(),
        title = title.orEmpty(),
        phones = phones.map { it.toCommon() },
        emails = emails.map { it.toCommon() },
        urls = urls,
        addresses = addresses.map {
            BarcodeType.Address(
                addressLines = it.addressLines.toList(),
                type = when (it.type) {
                    MlkitBarcodeAddress.TYPE_HOME -> BarcodeType.Address.Type.HOME
                    MlkitBarcodeAddress.TYPE_WORK -> BarcodeType.Address.Type.WORK
                    else -> BarcodeType.Address.Type.UNKNOWN
                },
            )
        },
    )
}

private fun MlkitBarcodePhone.toCommon(): BarcodeType.Phone {
    return BarcodeType.Phone(
        number = number.orEmpty(),
        type = when (type) {
            MlkitBarcodePhone.TYPE_WORK -> BarcodeType.Phone.Type.WORK
            MlkitBarcodePhone.TYPE_HOME -> BarcodeType.Phone.Type.HOME
            MlkitBarcodePhone.TYPE_FAX -> BarcodeType.Phone.Type.FAX
            MlkitBarcodePhone.TYPE_MOBILE -> BarcodeType.Phone.Type.MOBILE
            else -> BarcodeType.Phone.Type.UNKNOWN
        },
    )
}

private fun MlkitBarcodeEmail.toCommon(): BarcodeType.Email {
    return BarcodeType.Email(
        address = address.orEmpty(),
        subject = subject.orEmpty(),
        body = body.orEmpty(),
        type = when (type) {
            MlkitBarcodeEmail.TYPE_WORK -> BarcodeType.Email.Type.WORK
            MlkitBarcodeEmail.TYPE_HOME -> BarcodeType.Email.Type.HOME
            else -> BarcodeType.Email.Type.UNKNOWN
        },
    )
}

private fun MlkitBarcodeDriverLicense.toCommon(): BarcodeType.DriverLicense {
    return BarcodeType.DriverLicense(
        documentType = documentType.orEmpty(),
        firstName = firstName.orEmpty(),
        middleName = middleName.orEmpty(),
        lastName = lastName.orEmpty(),
        gender = gender.orEmpty(),
        addressStreet = addressStreet.orEmpty(),
        addressCity = addressCity.orEmpty(),
        addressState = addressState.orEmpty(),
        addressZip = addressZip.orEmpty(),
        licenseNumber = licenseNumber.orEmpty(),
        issueDate = issueDate.orEmpty(),
        expiryDate = expiryDate.orEmpty(),
        birthDate = birthDate.orEmpty(),
        issuingCountry = issuingCountry.orEmpty(),
    )
}

private fun MlkitBarcodeCalendarEvent.toCommon(): BarcodeType.CalendarEvent {
    return BarcodeType.CalendarEvent(
        summary = summary.orEmpty(),
        description = description.orEmpty(),
        location = location.orEmpty(),
        organizer = organizer.orEmpty(),
        status = status.orEmpty(),
        start = start?.toCommon(),
        end = end?.toCommon(),
    )
}

private fun MlkitBarcodeCalendarDateTime.toCommon(): BarcodeType.CalendarEvent.DateTime {
    return BarcodeType.CalendarEvent.DateTime(
        year = year,
        month = month,
        day = day,
        hours = hours,
        minutes = minutes,
        seconds = seconds,
        isUtc = isUtc,
        rawValue = rawValue.orEmpty(),
    )
}
