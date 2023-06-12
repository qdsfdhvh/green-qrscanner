package com.seiko.greenqrscanner.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface BarcodeType {

    @Serializable
    @SerialName("unknown")
    object UnKnown : BarcodeType

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
        val start: DateTime?,
        val end: DateTime?,
    ) : BarcodeType {
        @Serializable
        @SerialName("date_time")
        data class DateTime(
            val year: Int,
            val month: Int,
            val day: Int,
            val hours: Int,
            val minutes: Int,
            val seconds: Int,
            val isUtc: Boolean,
            val rawValue: String,
        )
    }

    @Serializable
    @SerialName("person")
    data class PersonName(
        val formattedName: String?,
        val pronunciation: String?,
        val prefix: String?,
        val first: String?,
        val middle: String?,
        val last: String?,
        val suffix: String?,
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
