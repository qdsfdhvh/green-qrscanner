package com.seiko.greenqrscanner.strings

public object MRStrings {
    private val baseLocale: StringsEn = StringsEn

    private val locales: Map<String, Strings> = mapOf("zh" to StringsZh, "en" to StringsEn)

    public val text: String
        get() = baseLocale.text

    public val url: String
        get() = baseLocale.url

    public val email: String
        get() = baseLocale.email

    public val phone: String
        get() = baseLocale.phone

    public val wifi: String
        get() = baseLocale.wifi

    public val sms: String
        get() = baseLocale.sms

    public val geo: String
        get() = baseLocale.geo

    public val contract_info: String
        get() = baseLocale.contract_info

    public val driver_license: String
        get() = baseLocale.driver_license

    public val calendar_event: String
        get() = baseLocale.calendar_event

    public val unknown: String
        get() = baseLocale.unknown

    public val ok: String
        get() = baseLocale.ok
}
