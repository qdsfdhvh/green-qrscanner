package com.seiko.greenqrscanner.data.model

data class Barcode(
    val rawValue: String,
    val format: BarcodeFormat,
    val type: BarcodeType?,
)

sealed interface BarcodeType {
    object Text : BarcodeType

    object ISBN : BarcodeType

    object Product : BarcodeType

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

    data class UrlBookmark(
        val title: String,
        val url: String,
    ) : BarcodeType

    data class Sms(
        val message: String,
        val phoneNumber: String,
    ) : BarcodeType

    data class GeoPoint(
        val lat: Double,
        val lng: Double,
    ) : BarcodeType

    data class ContactInfo(
        val name: PersonName?,
        val organization: String,
        val title: String,
        val phones: List<Phone>,
        val emails: List<Email>,
        val urls: List<String>,
        val addresses: List<Address>,
    ) : BarcodeType

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

    data class CalendarEvent(
        val summary: String,
        val description: String,
        val location: String,
        val organizer: String,
        val status: String,
        val start: DateTime?,
        val end: DateTime?,
    ) : BarcodeType {
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

    data class PersonName(
        val formattedName: String?,
        val pronunciation: String?,
        val prefix: String?,
        val first: String?,
        val middle: String?,
        val last: String?,
        val suffix: String?,
    )

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

enum class BarcodeFormat {
    UNKNOWN,
    FORMAT_1D,
    FORMAT_2D
}
