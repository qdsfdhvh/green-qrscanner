package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.model.name
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.OverflowTextSelection
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import com.seiko.greenqrscanner.util.decodeUrl
import com.seiko.uistate.UiState
import com.seiko.uistate.map
import com.seiko.uistate.onLoading
import com.seiko.uistate.onSuccess
import io.github.alexzhirkevich.qrose.rememberQrCodePainter
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Path
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.Navigator

@NavGraphDestination(
    route = Route.Detail.path,
)
@Composable
fun DetailSceneRoute(
    navigator: Navigator,
    @Path(Route.paramBarcode) barcodeBase64: String,
) {
    DetailScene(
        navigator = navigator,
        barcode = remember { barcodeBase64.decodeUrl() },
    )
}

@Composable
fun DetailScene(
    navigator: Navigator,
    barcode: String,
) {
    val status by producePresenter { DetailPresenter(barcode) }
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    BackButton {
                        navigator.goBack()
                    }
                },
                title = {
                    Text("详情")
                },
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize(), Alignment.Center) {
            status.onSuccess {
                DetailSuccessContent(
                    status = it,
                    clickable = DetailSceneClickable(
                        onStarClicked = {
                            it.setStar(it.barcode)
                        },
                        onFullContentClicked = {
                            navigator.navigate(Route.DetailFullContent(barcode))
                        },
                    ),
                    modifier = Modifier.matchParentSize(),
                )
            }.onLoading {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun DetailSuccessContent(
    status: DetailStatus,
    clickable: DetailSceneClickable,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Spacer(Modifier.height(16.dp))
        }
        item {
            Text(status.barcode.type.name)
        }
        item {
            Spacer(Modifier.height(8.dp))
        }
        item {
            QrCodeContent(
                barcode = status.barcode,
                modifier = Modifier.size(300.dp),
            )
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
        item {
            ButtonRow(
                barcode = status.barcode,
                onStarClick = clickable.onStarClicked,
            )
        }
        item {
            Spacer(Modifier.height(16.dp))
        }
        item {
            BarcodeRawValueContent(
                barcode = status.barcode,
                onFullContentClick = clickable.onFullContentClicked,
            )
        }
    }
}

@Composable
private fun QrCodeContent(
    barcode: UiBarcode,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier,
        shape = MaterialTheme.shapes.extraLarge,
        tonalElevation = 1.dp,
    ) {
        Image(
            rememberQrCodePainter(barcode.rawValue),
            contentDescription = "qrcode",
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
        )
    }
}

@Composable
private fun ButtonRow(
    barcode: UiBarcode,
    onStarClick: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        FilledTonalIconButton(onClick = onStarClick) {
            Icon(
                if (barcode.isStar) {
                    Icons.Rounded.Star
                } else {
                    Icons.Rounded.StarOutline
                },
                contentDescription = "star",
            )
        }
        FilledTonalIconButton(onClick = {}) {
            Icon(
                Icons.Rounded.Share,
                contentDescription = "share",
            )
        }
    }
}

@Composable
private fun BarcodeRawValueContent(
    barcode: UiBarcode,
    onFullContentClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.padding(horizontal = 16.dp),
        shape = MaterialTheme.shapes.extraLarge,
        tonalElevation = 1.dp,
    ) {
        OverflowTextSelection(
            barcode.rawValue,
            onFullContentClick = onFullContentClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .heightIn(min = 100.dp),
            maxLines = 6,
        )
    }
}

@Composable
private fun DetailPresenter(
    barcode: String,
    barcodeRepository: BarcodeRepository = rememberInject(),
    qrCodeRepository: BarcodeRepository = rememberInject(),
    appCoroutineDispatcher: AppCoroutineDispatcher = rememberInject(),
): UiState<DetailStatus> {
    val state by produceState(UiState.loading()) {
        qrCodeRepository.load(barcode).collect {
            value = UiState.success(it)
        }
    }
    return state.map { uiBarcode ->
        DetailStatus(
            barcode = uiBarcode,
            event = object : DetailEvent {
                override fun setStar(item: UiBarcode) {
                    barcodeRepository.setStar(
                        barcode = item.rawValue,
                        isStar = !item.isStar,
                    )
                }
            },
        )
    }
}

interface DetailEvent {
    fun setStar(item: UiBarcode)
}

@Immutable
data class DetailStatus(
    val barcode: UiBarcode,
    private val event: DetailEvent,
) : DetailEvent by event

private data class DetailSceneClickable(
    val onStarClicked: () -> Unit,
    val onFullContentClicked: () -> Unit,
)
