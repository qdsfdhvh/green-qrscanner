package com.seiko.greenqrscanner.data.repo

import androidx.compose.runtime.Immutable
import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneNotNull
import app.cash.sqldelight.paging3.QueryPagingSource
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.seiko.greenqrscanner.DbBarcode
import com.seiko.greenqrscanner.DbBarcodeQueries
import com.seiko.greenqrscanner.data.mapper.toUi
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import com.seiko.greenqrscanner.util.currentTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Singleton
@Provides
@Immutable
class BarcodeRepository(
    private val dbBarcodeQueries: DbBarcodeQueries,
    private val appCoroutineDispatcher: AppCoroutineDispatcher,
) {

    private val scope = CoroutineScope(SupervisorJob() + appCoroutineDispatcher.io)

    fun upset(barcodes: List<Barcode>) = scope.launch {
        dbBarcodeQueries.transaction {
            barcodes.forEach { upset(it) }
        }
    }

    fun upset(barcode: Barcode) = scope.launch {
        dbBarcodeQueries.upset(
            barcode = barcode.rawValue,
            barcode_format = barcode.format,
            barcode_type = barcode.type,
            update_time = currentTime(),
        )
    }

    fun delete(barcode: String) = scope.launch {
        dbBarcodeQueries.delete(barcode)
    }

    fun setStar(barcode: String, isStar: Boolean) = scope.launch {
        dbBarcodeQueries.setStar(if (isStar) 1 else 0, barcode)
    }

    fun load(barcode: String): Flow<UiBarcode> {
        return dbBarcodeQueries.load(barcode).asFlow()
            .mapToOneNotNull(appCoroutineDispatcher.io)
            .map { it.toUi() }
    }

    fun getHistory(): PagingSource<Int, DbBarcode> = QueryPagingSource(
        countQuery = dbBarcodeQueries.getHistoryCount(),
        transacter = dbBarcodeQueries,
        context = appCoroutineDispatcher.io,
        queryProvider = { limit, offset ->
            dbBarcodeQueries.getHistory(limit, offset)
        },
    )

    fun getStars(): PagingSource<Int, DbBarcode> = QueryPagingSource(
        countQuery = dbBarcodeQueries.getStarsCount(),
        transacter = dbBarcodeQueries,
        context = appCoroutineDispatcher.io,
        queryProvider = { limit, offset ->
            dbBarcodeQueries.getStars(limit, offset)
        },
    )
}
