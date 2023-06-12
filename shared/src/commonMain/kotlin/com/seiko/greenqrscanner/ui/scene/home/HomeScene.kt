package com.seiko.greenqrscanner.ui.scene.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.map
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.mapper.toUi
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.Route
import compose.icons.TablerIcons
import compose.icons.tablericons.DotsVertical
import kotlinx.coroutines.flow.map
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScene(
    navigator: Navigator,
) {
    val status by producePresenter { HomePresenter() }
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding).fillMaxSize(),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(status.pagingItems) { item ->
                item?.let {
                    BarcodeItem(
                        item = it,
                        onClick = {
                            navigator.navigate(
                                Route.Detail(it.rawValue),
                                NavOptions(
                                    launchSingleTop = true,
                                ),
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BarcodeItem(
    item: UiBarcode,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(onClick = onClick, modifier = modifier, shape = MaterialTheme.shapes.medium) {
        ListItem(
            leadingContent = {
                Icon(
                    item.icon,
                    contentDescription = item.title,
                )
            },
            headlineText = {
                Text(item.title)
            },
            supportingText = {
                BarcodeTypeContent(
                    type = item.type,
                    rawValue = item.rawValue,
                )
            },
            trailingContent = {
                Icon(
                    TablerIcons.DotsVertical,
                    contentDescription = "more setting",
                )
            },
        )
    }
}

@Composable
private fun BarcodeTypeContent(
    type: BarcodeType,
    rawValue: String,
) {
    when (type) {
        BarcodeType.Text,
        BarcodeType.ISBN,
        BarcodeType.Product -> {
            Text(rawValue)
        }
        is BarcodeType.Wifi -> {
            Column {
                Text("ssid: ${type.ssid}")
                Text("password: ${type.password}")
                Text("type: ${type.wifiType.name}")
            }
        }
        is BarcodeType.UrlBookmark -> {
            Column {
                val uriHandler = LocalUriHandler.current
                Text(
                    type.url,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        uriHandler.openUri(type.url)
                    },
                )
                if (type.title.isNotEmpty()) {
                    Text(type.title)
                }
            }
        }
        else -> {
            Text(rawValue)
        }
    }
}

@Composable
private fun HomePresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): HomeStatus {
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
        }
    }.collectAsLazyPagingItems()
    return HomeStatus(
        pagingItems = pagingItems,
    )
}

private data class HomeStatus(
    val pagingItems: LazyPagingItems<UiBarcode>,
)
