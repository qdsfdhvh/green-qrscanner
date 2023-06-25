package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.model.BarcodeFormat
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTextContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter(true) { AddTextContentPresenter() }
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddBarcodeTypeTitle(AddBarcodeType.Text, Modifier.fillMaxWidth())
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = status.text,
            onValueChange = { status.changeText(it) },
            modifier = Modifier.fillMaxWidth().height(200.dp),
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                status.done()
                onDone()
            },
            enabled = status.canDone,
        ) {
            Text("Done")
        }
    }
}

@Composable
private fun AddTextContentPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): AddTextContentStatus {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val canDone by remember {
        derivedStateOf {
            text.text.isNotEmpty()
        }
    }
    return AddTextContentStatus(
        text = text,
        canDone = canDone,
        event = object : AddTextContentEvent {
            override fun changeText(value: TextFieldValue) {
                text = value
            }

            override fun done() {
                barcodeRepository.upset(
                    Barcode(
                        rawValue = text.text,
                        format = BarcodeFormat.FORMAT_2D,
                        type = BarcodeType.Text,
                    ),
                )
            }
        },
    )
}

private interface AddTextContentEvent {
    fun changeText(value: TextFieldValue)
    fun done()
}

private data class AddTextContentStatus(
    val text: TextFieldValue,
    val canDone: Boolean,
    private val event: AddTextContentEvent,
) : AddTextContentEvent by event
