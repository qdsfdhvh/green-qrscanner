package com.seiko.greenqrscanner.data.db

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.util.storage.StorageService
import com.seiko.greenqrscanner.util.storage.databaseDir

@Singleton
@Provides
internal actual class DriverFactory(
    private val storageService: StorageService,
) {
    actual fun createDriver(
        schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
        dbName: String,
    ): SqlDriver {
        val driver = JdbcSqliteDriver("$JDBC_PREFIX${storageService.databaseDir.resolve(dbName)}")
        schema.create(driver)
        return driver
    }

    companion object {
        private const val JDBC_PREFIX = "jdbc:sqlite:"
    }
}
