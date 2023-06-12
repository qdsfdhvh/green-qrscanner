package com.seiko.greenqrscanner.data.db.adapter

import app.cash.sqldelight.ColumnAdapter
import com.seiko.greenqrscanner.util.decodeJson
import com.seiko.greenqrscanner.util.encodeJson
import kotlinx.serialization.KSerializer

internal class JsonColumnAdapter<T : Any>(
    private val serializer: KSerializer<T>
) : ColumnAdapter<T, String> {
    override fun decode(databaseValue: String) = databaseValue.decodeJson(serializer)

    override fun encode(value: T) = value.encodeJson(serializer)
}
