package com.seiko.greenqrscanner.data.repo

import app.cash.sqldelight.paging3.QueryPagingSource
import app.com.seiko.greenqrscanner.DbBarcodeQueries
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import com.seiko.greenqrscanner.util.currentTime

@Singleton
@Provides
class BarcodeRepository(
    private val dbBarcodeQueries: DbBarcodeQueries,
    private val appCoroutineDispatcher: AppCoroutineDispatcher,
) {

    fun upset(barcode: String) {
        dbBarcodeQueries.upset(
            barcode = barcode,
            update_time = currentTime(),
        )
    }

    fun getHistory() = QueryPagingSource(
        countQuery = dbBarcodeQueries.count(),
        transacter = dbBarcodeQueries,
        context = appCoroutineDispatcher.io,
        queryProvider = { limit, offset ->
            dbBarcodeQueries.getHistory(limit, offset)
        },
    )
}
