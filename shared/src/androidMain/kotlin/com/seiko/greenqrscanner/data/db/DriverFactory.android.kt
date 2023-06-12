package com.seiko.greenqrscanner.data.db

import android.content.Context
import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton

@Singleton
@Provides
internal actual class DriverFactory(
    private val context: Context,
) {
    actual fun createDriver(
        schema: SqlSchema<QueryResult.Value<Unit>>,
        dbName: String,
    ): SqlDriver {
        return AndroidSqliteDriver(schema, context, dbName)
    }
}
