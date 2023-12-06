package com.seiko.greenqrscanner.data.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema

internal expect class DriverFactory {
    fun createDriver(schema: SqlSchema<QueryResult.AsyncValue<Unit>>, dbName: String): SqlDriver
}
