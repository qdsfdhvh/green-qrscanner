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
import com.seiko.greenqrscanner.data.model.rawValue
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.AddBarcodeTypeTitle
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWifiContent(
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
            value = status.ssid,
            onValueChange = { status.changeSsid(it) },
            label = { Text("SSID") },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = NextKeyboardOptions,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = status.password,
            onValueChange = { status.changePassword(it) },
            label = { Text("Password") },
            maxLines = 1,
            singleLine = true,
            keyboardOptions = DoneKeyboardOptions,
            modifier = Modifier.fillMaxWidth(),
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
): AddWifiContentStatus {
    var ssid by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val canDone by remember {
        derivedStateOf {
            ssid.text.isNotBlank() && password.text.isNotBlank()
        }
    }
    return AddWifiContentStatus(
        ssid = ssid,
        password = password,
        canDone = canDone,
        event = object : AddWifiContentEvent {
            override fun changeSsid(value: TextFieldValue) {
                ssid = value
            }

            override fun changePassword(value: TextFieldValue) {
                password = value
            }

            override fun done() {
                val type = BarcodeType.Wifi(
                    ssid = ssid.text,
                    password = password.text,
                    wifiType = BarcodeType.Wifi.Type.OPEN,
                )
                barcodeRepository.upset(
                    Barcode(
                        rawValue = type.rawValue,
                        format = BarcodeFormat.FORMAT_2D,
                        type = type,
                    ),
                )
            }
        },
    )
}

private interface AddWifiContentEvent {
    fun changeSsid(value: TextFieldValue)
    fun changePassword(value: TextFieldValue)
    fun done()
}

private data class AddWifiContentStatus(
    val ssid: TextFieldValue,
    val password: TextFieldValue,
    val canDone: Boolean,
    private val event: AddWifiContentEvent,
) : AddWifiContentEvent by event
