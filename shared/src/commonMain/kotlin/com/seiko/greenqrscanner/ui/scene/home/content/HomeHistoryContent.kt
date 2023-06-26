package com.seiko.greenqrscanner.ui.scene.home.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.map
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.flow.immutable
import com.seiko.greenqrscanner.data.mapper.toUi
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.model.name
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.BarcodeItem
import com.seiko.greenqrscanner.ui.widget.BarcodeItemClickable
import kotlinx.coroutines.flow.map
import moe.tlaster.precompose.molecule.producePresenter

@Composable
fun HomeHistoryContent(
    barcodeItemClickable: BarcodeItemClickable,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter { HomeHistoryPresenter() }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(
            count = status.pagingItems.itemCount,
            key = status.pagingItems.itemKey { it.rawValue },
            contentType = status.pagingItems.itemContentType { it.type.name },
        ) { index ->
            status.pagingItems[index]?.let {
                BarcodeItem(
                    item = it,
                    clickable = barcodeItemClickable,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun HomeHistoryPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): HomeHistoryStatus {
    val pagingItems = remember {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                barcodeRepository.getHistory()
            },
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUi()
            }
        }.immutable()
    }.collectAsLazyPagingItems()
    return HomeHistoryStatus(
        pagingItems = pagingItems,
    )
}

private data class HomeHistoryStatus(
    val pagingItems: LazyPagingItems<UiBarcode>,
)
