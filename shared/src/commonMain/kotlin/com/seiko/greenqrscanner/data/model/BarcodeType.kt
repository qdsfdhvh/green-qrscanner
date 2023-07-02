package com.seiko.greenqrscanner.data.model

import androidx.compose.runtime.Immutable
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
@Serializable
sealed interface BarcodeType {

    @Serializable
    @SerialName("unknown")
    object Unknown : BarcodeType

    @Serializable
    @SerialName("text")
    object Text : BarcodeType

    @Serializable
    @SerialName("isbn")
    object ISBN : BarcodeType

    @Serializable
    @SerialName("product")
    object Product : BarcodeType

    @Serializable
    @SerialName("wifi")
    data class Wifi(
        val ssid: String,
        val password: String,
        val wifiType: Type,
    ) : BarcodeType {
        enum class Type {
            OPEN,
            WPA,
            WEP
        }
    }

    @Serializable
    @SerialName("url")
    data class UrlBookmark(
        val title: String,
        val url: String,
    ) : BarcodeType

    @Serializable
    @SerialName("sms")
    data class Sms(
        val message: String,
        val phoneNumber: String,
    ) : BarcodeType

    @Serializable
    @SerialName("geo")
    data class GeoPoint(
        val lat: Double,
        val lng: Double,
    ) : BarcodeType

    @Serializable
    @SerialName("contact_info")
    data class ContactInfo(
        val name: PersonName?,
        val organization: String,
        val title: String,
        val phones: List<Phone>,
        val emails: List<Email>,
        val urls: List<String>,
        val addresses: List<Address>,
    ) : BarcodeType

    @Serializable
    @SerialName("email")
    data class Email(
        val address: String,
        val subject: String,
        val body: String,
        val type: Type,
    ) : BarcodeType {
        enum class Type {
            UNKNOWN,
            WORK,
            HOME
        }
    }

    @Serializable
    @SerialName("phone")
    data class Phone(
        val number: String,
        val type: Type,
    ) : BarcodeType {
        enum class Type {
            UNKNOWN,
            WORK,
            HOME,
            FAX,
            MOBILE
        }
    }

    @Serializable
    @SerialName("driver_license")
    data class DriverLicense(
        val documentType: String,
        val firstName: String,
        val middleName: String,
        val lastName: String,
        val gender: String,
        val addressStreet: String,
        val addressCity: String,
        val addressState: String,
        val addressZip: String,
        val licenseNumber: String,
        val issueDate: String,
        val expiryDate: String,
        val birthDate: String,
        val issuingCountry: String,
    ) : BarcodeType

    @Serializable
    @SerialName("calendar_event")
    data class CalendarEvent(
        val summary: String,
        val description: String,
        val location: String,
        val organizer: String,
        val status: String,
        val start: LocalDateTime?,
        val end: LocalDateTime?,
    ) : BarcodeType

    @Serializable
    @SerialName("person")
    data class PersonName(
        val formattedName: String?,
        val pronunciation: String? = null,
        val prefix: String? = null,
        val first: String? = null,
        val middle: String? = null,
        val last: String? = null,
        val suffix: String? = null,
    )

    @Serializable
    @SerialName("address")
    data class Address(
        val addressLines: List<String>,
        val type: Type,
    ) {
        enum class Type {
            UNKNOWN,
            WORK,
            HOME
        }
    }
}

val BarcodeType.title: String
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

val BarcodeType.rawValue: String
    get() = when (this) {
        is BarcodeType.ContactInfo -> """
            BEGIN:GREEN
            N:${name?.formattedName};
            ORG:$organization;
            TITLE:$title;
            ${phones.joinToString("\n") { "TEL;TYPE=${it.type.name}:${it.number}" }}
            ${emails.joinToString("\n") { "EMAIL;TYPE=${it.type.name}:${it.address}" }}
            ${urls.joinToString("\n") { "URL:$it" }}
            ${addresses.joinToString("\n") { "ADR;TYPE=${it.type.name}:${it.addressLines.joinToString(";") { address -> address }}" }}
            END:GREEN
        """.trimIndent()
        else -> error("don't use BarcodeType.rawValue for $this")
    }

val BarcodeType.name: String
    get() = when (this) {
        BarcodeType.Unknown -> "Unknown"
        BarcodeType.Text -> "Text"
        BarcodeType.ISBN -> "ISBN"
        BarcodeType.Product -> "Product"
        is BarcodeType.Wifi -> "Wifi"
        is BarcodeType.UrlBookmark -> "Url"
        is BarcodeType.Sms -> "Sms"
        is BarcodeType.GeoPoint -> "Geo"
        is BarcodeType.ContactInfo -> "ContactInfo"
        is BarcodeType.Email -> "Email"
        is BarcodeType.Phone -> "Phone"
        is BarcodeType.DriverLicense -> "DriverLicense"
        is BarcodeType.CalendarEvent -> "CalendarEvent"
    }
