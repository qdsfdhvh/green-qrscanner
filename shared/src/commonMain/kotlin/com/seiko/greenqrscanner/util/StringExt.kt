package com.seiko.greenqrscanner.util

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@OptIn(ExperimentalEncodingApi::class)
fun String.encodeUrl(): String {
    return Base64.UrlSafe.encode(encodeToByteArray())
}

@OptIn(ExperimentalEncodingApi::class)
fun String.decodeUrl(): String {
    return Base64.UrlSafe.decode(this).decodeToString()
}

fun String.isUrl(): Boolean {
    val regex = Regex("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]")
    return regex.matches(this)
}
