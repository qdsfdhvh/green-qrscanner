package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.seiko.greenqrscanner.ui.widget.DateTextField
import com.seiko.greenqrscanner.ui.widget.TimeTextField
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import moe.tlaster.precompose.molecule.producePresenter

@Composable
fun AddCalendarEventContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter { AddCalendarEventContentPresenter() }
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddBarcodeTypeTitle(AddBarcodeType.CalendarEvent, Modifier.fillMaxWidth())
        Spacer(Modifier.height(6.dp))
        Row {
            DateTextField(
                selectedDate = status.startDate,
                onDateSelected = { status.updateStartDate(it) },
                dialogTitle = "Date",
            )
            Spacer(Modifier.width(8.dp))
            TimeTextField(
                selectedTime = status.startTime,
                onTimeSelected = { status.updateStartTime(it) },
                dialogTitle = "Time",
            )
        }
        Spacer(Modifier.height(32.dp))
        Button(
            onClick = {
                onDone()
            },
        ) {
            Text("Done")
        }
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
    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }
    var endTime by remember { mutableStateOf<LocalTime?>(null) }
    return AddCalendarEventContentStatus(
        startDate = startDate,
        startTime = startTime,
        endDate = endDate,
        endTime = endTime,
        canDone = canDone,
        event = object : AddCalendarEventContentEvent {
            override fun updateStartDate(date: LocalDate) {
                startDate = date
            }

            override fun updateStartTime(time: LocalTime) {
                startTime = time
            }

            override fun updateEndDate(date: LocalDate) {
                endDate = date
            }

            override fun updateEndTime(time: LocalTime) {
                endTime = time
            }

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
                    ),
                )
            }
        },
    )
}

private interface AddCalendarEventContentEvent {
    fun updateStartDate(date: LocalDate)
    fun updateStartTime(time: LocalTime)
    fun updateEndDate(date: LocalDate)
    fun updateEndTime(time: LocalTime)
    fun done()
}

private data class AddCalendarEventContentStatus(
    val startDate: LocalDate?,
    val startTime: LocalTime?,
    val endDate: LocalDate?,
    val endTime: LocalTime?,
    val canDone: Boolean,
    private val event: AddCalendarEventContentEvent,
) : AddCalendarEventContentEvent by event
