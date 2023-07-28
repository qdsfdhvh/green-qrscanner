package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
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
import com.seiko.greenqrscanner.data.model.toRawValue
import com.seiko.greenqrscanner.data.repo.BarcodeRepository
import com.seiko.greenqrscanner.ui.widget.AddBarcodeTypeTitle
import com.seiko.greenqrscanner.ui.widget.DateTextField
import com.seiko.greenqrscanner.ui.widget.TimeTextField
import com.seiko.greenqrscanner.util.AppDateFormatter
import com.seiko.greenqrscanner.util.copy
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.toLocalDateTime
import moe.tlaster.precompose.molecule.producePresenter

@Composable
fun AddCalendarEventContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter { AddCalendarEventContentPresenter() }
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item {
            AddBarcodeTypeTitle(AddBarcodeType.CalendarEvent, Modifier.fillMaxWidth())
        }
        item {
            OutlinedTextField(
                value = status.title,
                onValueChange = { status.updateTitle(it) },
                label = { Text("活动名称") },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = NextKeyboardOptions,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Text("开始时间")
        }
        item {
            Row {
                DateTextField(
                    selectedDate = status.startDate,
                    onDateSelected = { status.updateStartDate(it) },
                    dialogTitle = "Date",
                    minimumDate = status.minimumDate,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(8.dp))
                TimeTextField(
                    selectedTime = status.startTime,
                    onTimeSelected = { status.updateStartTime(it) },
                    dialogTitle = "Time",
                    modifier = Modifier.weight(1f),
                )
            }
        }
        item {
            Text("结束时间")
        }
        item {
            Row {
                DateTextField(
                    selectedDate = status.endDate,
                    onDateSelected = { status.updateEndDate(it) },
                    dialogTitle = "Date",
                    minimumDate = status.minimumDate,
                    modifier = Modifier.weight(1f),
                )
                Spacer(Modifier.width(8.dp))
                TimeTextField(
                    selectedTime = status.endTime,
                    onTimeSelected = { status.updateEndTime(it) },
                    dialogTitle = "Time",
                    modifier = Modifier.weight(1f),
                )
            }
        }
        item {
            OutlinedTextField(
                value = status.location,
                onValueChange = { status.updateLocation(it) },
                label = { Text("位置") },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = NextKeyboardOptions,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            OutlinedTextField(
                value = status.desc,
                onValueChange = { status.updateDesc(it) },
                label = { Text("描述") },
                keyboardOptions = DoneKeyboardOptions,
                modifier = Modifier.fillMaxWidth().height(200.dp),
            )
        }
        item {
            Spacer(Modifier.height((32 - 12).dp))
        }
        item {
            Box(Modifier.fillMaxWidth(), Alignment.Center) {
                Button(
                    onClick = {
                        status.done()
                        onDone()
                    },
                    enabled = status.canDone,
                    modifier = Modifier,
                ) {
                    Text("Done")
                }
            }
        }
    }
}

@Composable
private fun AddCalendarEventContentPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
    appDateFormatter: AppDateFormatter = rememberInject(),
): AddCalendarEventContentStatus {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    val today = remember { Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()) }
    var startDate by remember { mutableStateOf(today.date) }
    var startTime by remember { mutableStateOf(today.time) }
    var endDate by remember { mutableStateOf(today.date) }
    var endTime by remember { mutableStateOf(today.time) }
    var location by remember { mutableStateOf(TextFieldValue("")) }
    var desc by remember { mutableStateOf(TextFieldValue("")) }
    val canDone by remember {
        derivedStateOf {
            title.text.isNotEmpty()
        }
    }
    return AddCalendarEventContentStatus(
        title = title,
        minimumDate = today.date,
        startDate = startDate,
        startTime = startTime,
        endDate = endDate,
        endTime = endTime,
        location = location,
        desc = desc,
        canDone = canDone,
        event = object : AddCalendarEventContentEvent {
            override fun updateTitle(value: TextFieldValue) {
                title = value
            }

            override fun updateStartDate(date: LocalDate) {
                startDate = date
                if (date > endDate) endDate = date
            }

            override fun updateStartTime(time: LocalTime) {
                startTime = time
                if (startDate == endDate && time > endTime) endTime = time
            }

            override fun updateEndDate(date: LocalDate) {
                endDate = date
                if (date < startDate) startDate = date
            }

            override fun updateEndTime(time: LocalTime) {
                endTime = time
                if (startDate == endDate && time < startTime) startTime = time
            }

            override fun updateLocation(value: TextFieldValue) {
                location = value
            }

            override fun updateDesc(value: TextFieldValue) {
                desc = value
            }

            override fun done() {
                val type = BarcodeType.CalendarEvent(
                    summary = title.text,
                    description = desc.text,
                    location = location.text,
                    organizer = "",
                    status = "",
                    start = startDate.atTime(startTime.copy(nanosecond = 0)),
                    end = endDate.atTime(endTime.copy(nanosecond = 0)),
                )
                barcodeRepository.upset(
                    Barcode(
                        rawValue = type.toRawValue(),
                        format = BarcodeFormat.FORMAT_2D,
                        type = type,
                    ),
                )
            }
        },
    )
}

private interface AddCalendarEventContentEvent {
    fun updateTitle(value: TextFieldValue)
    fun updateStartDate(date: LocalDate)
    fun updateStartTime(time: LocalTime)
    fun updateEndDate(date: LocalDate)
    fun updateEndTime(time: LocalTime)
    fun updateLocation(value: TextFieldValue)
    fun updateDesc(value: TextFieldValue)
    fun done()
}

private data class AddCalendarEventContentStatus(
    val title: TextFieldValue,
    val minimumDate: LocalDate,
    val startDate: LocalDate,
    val startTime: LocalTime,
    val endDate: LocalDate,
    val endTime: LocalTime,
    val location: TextFieldValue,
    val desc: TextFieldValue,
    val canDone: Boolean,
    private val event: AddCalendarEventContentEvent,
) : AddCalendarEventContentEvent by event
