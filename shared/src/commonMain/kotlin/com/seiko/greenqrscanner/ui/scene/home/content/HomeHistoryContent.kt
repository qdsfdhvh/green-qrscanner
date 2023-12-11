package com.seiko.greenqrscanner.ui.scene.home.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.History
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.seiko.greenqrscanner.ui.scene.home.widget.BarcodeItem
import com.seiko.greenqrscanner.ui.scene.home.widget.BarcodeItemClickable
import kotlinx.coroutines.flow.map
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeHistoryContent(
    barcodeItemClickable: BarcodeItemClickable,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter { HomeHistoryPresenter() }
    Box(modifier) {
        var queryForUi by rememberSaveable { mutableStateOf(status.query) }
        var active by rememberSaveable { mutableStateOf(false) }
        DockedSearchBar(
            query = queryForUi,
            onQueryChange = { queryForUi = it },
            onSearch = {
                active = false
                status.onQueryChange(queryForUi)
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = { Text("search history") },
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "search icon",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            trailingIcon = {
                if (active) {
                    IconButton(onClick = {
                        active = false
                        queryForUi = ""
                        status.onQueryChange("")
                    }) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "close search",
                        )
                    }
                }
            },
            modifier = Modifier
                .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
        ) {
            repeat(1) { idx ->
                val resultText = "Suggestion $idx"
                ListItem(
                    modifier = Modifier.clickable {
                        queryForUi = resultText
                        active = false
                    },
                    headlineContent = {
                        Text(
                            text = resultText,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    leadingContent = {
                        Icon(Icons.Rounded.History, contentDescription = null)
                    },
                )
            }
        }

        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(
                start = 8.dp,
                end = 8.dp,
                top = 16.dp + 56.dp,
                bottom = 60.dp,
            ),
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
                        trail = {
                            IconButton(onClick = { barcodeItemClickable.onSettingClicked(it) }) {
                                Icon(
                                    Icons.Rounded.Settings,
                                    contentDescription = "barcode settings",
                                )
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun HomeHistoryPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): HomeHistoryStatus {
    var query: String by remember {
        mutableStateOf("")
    }
    val pagingItemsFlow by remember {
        derivedStateOf {
            // state can't update in pager lambda
            val updateQuery = query
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                    initialLoadSize = 20,
                ),
                pagingSourceFactory = {
                    barcodeRepository.getHistory(updateQuery)
                },
            ).flow.map { pagingData ->
                pagingData.map {
                    it.toUi()
                }
            }.immutable()
        }
    }
    return HomeHistoryStatus(
        query = query,
        pagingItems = pagingItemsFlow.collectAsLazyPagingItems(),
        event = object : HomeHistoryEvent {
            override fun onQueryChange(value: String) {
                query = value
            }
        },
    )
}

private interface HomeHistoryEvent {
    fun onQueryChange(value: String)
}

private data class HomeHistoryStatus(
    val query: String,
    val pagingItems: LazyPagingItems<UiBarcode>,
    private val event: HomeHistoryEvent,
) : HomeHistoryEvent by event
