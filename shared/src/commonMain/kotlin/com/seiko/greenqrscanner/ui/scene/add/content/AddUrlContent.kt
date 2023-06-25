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
import com.seiko.greenqrscanner.util.isUrl
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddUrlContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter(true) { AddUrlContentPresenter() }
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddBarcodeTypeTitle(AddBarcodeType.Text, Modifier.fillMaxWidth())
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = status.url,
            onValueChange = { status.event(AddUrlContentEvent.ChangeUrl(it)) },
            label = { Text("https://") },
            maxLines = 1,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = status.title,
            onValueChange = { status.event(AddUrlContentEvent.ChangeTitle(it)) },
            maxLines = 1,
            label = { Text("Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                status.event(AddUrlContentEvent.Done)
                onDone()
            },
            enabled = status.canDone,
        ) {
            Text("Done")
        }
    }
}

@Composable
private fun AddUrlContentPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): AddUrlContentStatus {
    var url by remember { mutableStateOf(TextFieldValue("")) }
    var title by remember { mutableStateOf(TextFieldValue("")) }
    val canDone by remember {
        derivedStateOf {
            url.text.isNotEmpty() && url.text.safeUrl().isUrl()
        }
    }
    return AddUrlContentStatus(
        url = url,
        title = title,
        canDone = canDone,
    ) { event ->
        when (event) {
            is AddUrlContentEvent.ChangeUrl -> {
                url = event.url
            }
            is AddUrlContentEvent.ChangeTitle -> {
                title = event.title
            }
            AddUrlContentEvent.Done -> {
                val safeUrl = url.text.safeUrl()
                barcodeRepository.upset(
                    Barcode(
                        rawValue = safeUrl,
                        format = BarcodeFormat.FORMAT_1D,
                        type = BarcodeType.UrlBookmark(
                            title = title.text,
                            url = safeUrl,
                        ),
                    ),
                )
            }
        }
    }
}

private sealed interface AddUrlContentEvent {
    object Done : AddUrlContentEvent
    data class ChangeUrl(val url: TextFieldValue) : AddUrlContentEvent
    data class ChangeTitle(val title: TextFieldValue) : AddUrlContentEvent
}

private data class AddUrlContentStatus(
    val url: TextFieldValue,
    val title: TextFieldValue,
    val canDone: Boolean,
    val event: (AddUrlContentEvent) -> Unit,
)

private fun String.safeUrl(): String {
    return if (startsWith("http")) this else "https://$this"
}
