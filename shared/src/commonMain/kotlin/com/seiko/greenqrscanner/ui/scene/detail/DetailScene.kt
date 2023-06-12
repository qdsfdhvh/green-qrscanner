package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
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
            Text(barcode)
        }
    }
}

@Composable
private fun DetailPresenter(
    rawValue: String,
    qrCodeRepository: BarcodeRepository = rememberInject(),
): DetailStatus {
    LaunchedEffect(Unit) {
        qrCodeRepository.upset(rawValue)
    }
    return DetailStatus()
}

class DetailStatus
