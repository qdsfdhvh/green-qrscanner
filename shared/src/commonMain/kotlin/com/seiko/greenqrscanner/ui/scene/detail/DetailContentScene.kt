package com.seiko.greenqrscanner.ui.scene.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import moe.tlaster.precompose.navigation.Navigator

@Composable
fun DetailContentScene(
    navigator: Navigator,
    barcode: String,
) {
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
            Text(
                barcode,
            )
        }
    }
}