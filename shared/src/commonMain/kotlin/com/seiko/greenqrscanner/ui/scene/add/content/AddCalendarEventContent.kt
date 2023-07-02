package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moriatsushi.koject.compose.rememberInject
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.data.model.Barcode
import com.seiko.greenqrscanner.data.model.BarcodeFormat
import com.seiko.greenqrscanner.data.model.BarcodeType
import com.seiko.greenqrscanner.data.model.rawValue
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.AddBarcodeTypeTitle
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import kotlinx.datetime.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCalendarEventContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddBarcodeTypeTitle(AddBarcodeType.CalendarEvent, Modifier.fillMaxWidth())
        Spacer(Modifier.height(6.dp))
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                onDone()
            },
        ) {
            Text("Done")
        }
    }

    val datePickerDialogState = rememberMaterialDialogState()
    MaterialDialog(
        dialogState = datePickerDialogState,
        buttons = {
            // positiveButton("Ok")
            // negativeButton("Cancel")
        }
    ) {
        datepicker {

        }
    }
    // val timePickerDialogState = rememberMaterialDialogState()
    // MaterialDialog(
    //     dialogState = datePickerDialogState,
    //     buttons = {
    //
    //     }
    // ) {
    //     timepicker {
    //
    //     }
    // }

    LaunchedEffect(Unit) {
        datePickerDialogState.show()
    }
}

@Composable
private fun AddCalendarEventContentPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): AddCalendarEventContentStatus {
    val canDone by remember {
        derivedStateOf {
            true
        }
    }
    return AddCalendarEventContentStatus(
        canDone = canDone,
        event = object : AddCalendarEventContentEvent {
            override fun done() {
                val type = BarcodeType.CalendarEvent(
                    summary = "",
                    description = "",
                    location = "",
                    organizer = "",
                    status = "",
                    start = LocalDateTime.parse("2019-10-01T18:12"),
                    end = LocalDateTime.parse("2019-10-01T18:12"),
                )
                barcodeRepository.upset(
                    Barcode(
                        rawValue = type.rawValue,
                        format = BarcodeFormat.FORMAT_2D,
                        type = type,
                    )
                )
            }
        }
    )
}

private interface AddCalendarEventContentEvent {
    fun done()
}

private data class AddCalendarEventContentStatus(
    val canDone: Boolean,
    private val event: AddCalendarEventContentEvent,
) : AddCalendarEventContentEvent by event
