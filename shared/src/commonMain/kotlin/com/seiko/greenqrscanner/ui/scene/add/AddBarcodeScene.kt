package com.seiko.greenqrscanner.ui.scene.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.ui.scene.add.content.AddTextContent
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBarcodeScene(
    navigator: Navigator,
    type: AddBarcodeType,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    BackButton {
                        navigator.goBack()
                    }
                },
                title = {
                    Text("Add")
                },
                actions = {
                    IconButton(onClick = { /* onCreate */ }) {
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize(), Alignment.Center) {
            when (type) {
                AddBarcodeType.Text -> AddTextContent()
                else -> {
                    Text("support add type ${type.name}")
                }
            }
        }
    }
}
