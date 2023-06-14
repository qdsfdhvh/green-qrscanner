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
import androidx.paging.compose.items
import androidx.paging.map
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.mapper.toUi
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.BarcodeItem
import kotlinx.coroutines.flow.map
import moe.tlaster.precompose.molecule.producePresenter

@Composable
fun HomeStarContent(
    onBarcodeClick: (UiBarcode) -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter { HomeStarPresenter() }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(status.pagingItems) { item ->
            item?.let {
                BarcodeItem(
                    item = it,
                    onClick = { onBarcodeClick(it) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Composable
private fun HomeStarPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): HomeStarStatus {
    val pagingItems = remember {
        Pager(
            config = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20,
            ),
            pagingSourceFactory = {
                barcodeRepository.getStars()
            },
        ).flow.map { pagingData ->
            pagingData.map {
                it.toUi()
            }
        }
    }.collectAsLazyPagingItems()
    return HomeStarStatus(
        pagingItems = pagingItems,
    )
}

private data class HomeStarStatus(
    val pagingItems: LazyPagingItems<UiBarcode>,
)
