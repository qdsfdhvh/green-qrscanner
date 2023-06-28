package com.seiko.greenqrscanner.ui.scene.add.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.ui.widget.AddBarcodeTypeTitle
import com.seiko.greenqrscanner.ui.widget.Dialog

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
    var displayDatePicker by remember { mutableStateOf(false) }
    if (displayDatePicker) {
        Dialog(
            onDismissRequest = {
                displayDatePicker = false
            },
            modifier = Modifier.padding(horizontal = 32.dp),
        ) {
            Column(Modifier.fillMaxWidth()) {
                val datePickerState = rememberDatePickerState()
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                )
            }
        }
    }
    var displayTimePicker by remember { mutableStateOf(true) }
    if (displayTimePicker) {
        Dialog(
            onDismissRequest = {
                displayTimePicker = false
            },
            modifier = Modifier.padding(horizontal = 32.dp),
        ) {
            Box(Modifier.fillMaxWidth(), Alignment.Center) {
                val datePickerState = rememberTimePickerState()
                TimePicker(
                    state = datePickerState,
                )
            }
        }
    }
}
