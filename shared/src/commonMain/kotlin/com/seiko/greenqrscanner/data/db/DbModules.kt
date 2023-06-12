package com.seiko.greenqrscanner.data.db

import app.com.seiko.greenqrscanner.DbBarcodeQueries
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.data.model.AppDatabase

@Singleton
@Provides
internal fun createAppDatabase(driverFactory: DriverFactory): AppDatabase {
    return AppDatabase(
        driverFactory.createDriver(AppDatabase.Schema, "app.db"),
    )
}

@Provides
internal fun provideDbBarcodeQueries(
    appDatabase: AppDatabase,
): DbBarcodeQueries {
    return appDatabase.dbBarcodeQueries
}
