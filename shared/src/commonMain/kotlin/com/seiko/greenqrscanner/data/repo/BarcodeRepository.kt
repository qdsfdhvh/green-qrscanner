package com.seiko.greenqrscanner.data.repo

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneNotNull
import app.cash.sqldelight.paging3.QueryPagingSource
import app.com.seiko.greenqrscanner.DbBarcodeQueries
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.data.mapper.toUi
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import com.seiko.greenqrscanner.util.currentTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
@Provides
class BarcodeRepository(
    private val dbBarcodeQueries: DbBarcodeQueries,
    private val appCoroutineDispatcher: AppCoroutineDispatcher,
) {

    fun upset(barcodes: List<Barcode>) {
        dbBarcodeQueries.transaction {
            barcodes.forEach { upset(it) }
        }
    }

    fun upset(barcode: Barcode) {
        dbBarcodeQueries.upset(
            barcode = barcode.rawValue,
            barcode_format = barcode.format,
            barcode_type = barcode.type,
            update_time = currentTime(),
        )
    }

    fun load(barcode: String): Flow<UiBarcode> {
        return dbBarcodeQueries.load(barcode).asFlow()
            .mapToOneNotNull(appCoroutineDispatcher.io)
            .map { it.toUi() }
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
