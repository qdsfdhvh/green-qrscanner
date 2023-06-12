package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.UiBarcode
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import com.seiko.uistate.UiState
import com.seiko.uistate.map
import com.seiko.uistate.onLoading
import com.seiko.uistate.onSuccess
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
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
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize(), Alignment.Center) {
            status.onSuccess {
                Text(it.barcode.rawValue)
            }.onLoading {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
private fun DetailPresenter(
    barcode: String,
    qrCodeRepository: BarcodeRepository = rememberInject(),
): UiState<DetailStatus> {
    val state by produceState(UiState.loading()) {
        qrCodeRepository.load(barcode).collect {
            value = UiState.success(it)
        }
    }
    return state.map {
        DetailStatus(
            barcode = it,
        )
    }
}

@Immutable
data class DetailStatus(
    val barcode: UiBarcode,
)
