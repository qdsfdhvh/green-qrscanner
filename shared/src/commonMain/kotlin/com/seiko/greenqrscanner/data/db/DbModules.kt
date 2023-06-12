package com.seiko.greenqrscanner.data.db

import app.com.seiko.greenqrscanner.DbBarcode
import app.com.seiko.greenqrscanner.DbBarcodeQueries
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.data.db.adapter.JsonColumnAdapter
import com.seiko.greenqrscanner.data.model.AppDatabase
import com.seiko.greenqrscanner.data.model.BarcodeFormat
import com.seiko.greenqrscanner.data.model.BarcodeType

@Singleton
@Provides
internal fun createAppDatabase(driverFactory: DriverFactory): AppDatabase {
    return AppDatabase(
        driverFactory.createDriver(AppDatabase.Schema, "app.db"),
        DbBarcode.Adapter(
            formatAdapter = JsonColumnAdapter(BarcodeFormat.serializer()),
            typeAdapter = JsonColumnAdapter(BarcodeType.serializer()),
        ),
    )
}

@Provides
internal fun provideDbBarcodeQueries(
    appDatabase: AppDatabase,
): DbBarcodeQueries {
    return appDatabase.dbBarcodeQueries
}
