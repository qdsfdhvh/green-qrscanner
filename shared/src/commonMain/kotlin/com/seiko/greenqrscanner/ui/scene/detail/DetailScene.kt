package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.OverflowTextSelection
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import com.seiko.greenqrscanner.util.decodeUrl
import com.seiko.greenqrscanner.util.generateQrCode
import com.seiko.uistate.UiState
import com.seiko.uistate.map
import com.seiko.uistate.onLoading
import com.seiko.uistate.onSuccess
import com.seiko.uistate.toUiState
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Path
import kotlinx.coroutines.withContext
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
    val clickable = remember {
        DetailSceneClickable(
            onFullContentClick = {
                navigator.navigate(Route.DetailFullContent(barcode))
            },
        )
    }
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    BackButton {
                        navigator.goBack()
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize(), Alignment.Center) {
            status.onSuccess {
                DetailSuccessContent(
                    status = it,
                    clickable = clickable,
                    modifier = Modifier.fillMaxWidth(),
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
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        item {
            OverflowTextSelection(
                status.barcode.rawValue,
                onFullContentClick = clickable.onFullContentClick,
            )
        }
        item {
            QrCodeContent(status.qrCodeState, Modifier.size(200.dp))
        }
    }
}

@Composable
private fun QrCodeContent(
    qrCodeState: UiState<ImageBitmap>,
    modifier: Modifier = Modifier,
) {
    Box(modifier, Alignment.Center) {
        qrCodeState.onSuccess { image ->
            Image(
                image,
                contentDescription = "qrcode",
                modifier = Modifier.fillMaxSize(),
            )
        }.onLoading {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun DetailPresenter(
    barcode: String,
    qrCodeRepository: BarcodeRepository = rememberInject(),
    appCoroutineDispatcher: AppCoroutineDispatcher = rememberInject(),
): UiState<DetailStatus> {
    val state by produceState(UiState.loading()) {
        qrCodeRepository.load(barcode).collect {
            value = UiState.success(it)
        }
    }
    return state.map { uiBarcode ->
        val qrCodeState by produceState(
            UiState.loading(),
            key1 = uiBarcode.rawValue,
        ) {
            value = withContext(appCoroutineDispatcher.io) {
                runCatching {
                    generateQrCode(uiBarcode.rawValue, 200)
                }
            }.toUiState()
        }
        DetailStatus(
            barcode = uiBarcode,
            qrCodeState = qrCodeState,
        )
    }
}

@Immutable
data class DetailStatus(
    val barcode: UiBarcode,
    val qrCodeState: UiState<ImageBitmap>,
)

private data class DetailSceneClickable(
    val onFullContentClick: () -> Unit,
)
