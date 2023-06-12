package com.seiko.greenqrscanner.util

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

val JSON by lazy {
    Json {
        ignoreUnknownKeys = true
        isLenient = true
        coerceInputValues = true
    }
}

inline fun <reified T> T.encodeJson(): String = JSON.encodeToString(this)

inline fun <reified T> T.encodeJsonElement(): JsonElement = JSON.encodeToJsonElement(this)

inline fun <reified T> String.decodeJson(): T = JSON.decodeFromString(this)

inline fun <reified T> JsonElement.decodeJsonElement(): T = JSON.decodeFromJsonElement(this)
