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
import moe.tlaster.precompose.molecule.producePresenter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactInfoContent(
    onDone: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val status by producePresenter(true) { AddContactInfoContentPresenter() }
    Column(
        modifier = modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AddBarcodeTypeTitle(AddBarcodeType.ContactInfo, Modifier.fillMaxWidth())
        Spacer(Modifier.height(6.dp))
        OutlinedTextField(
            value = status.title,
            onValueChange = { status.changeTitle(it) },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = status.name,
            onValueChange = { status.changeName(it) },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = status.organization,
            onValueChange = { status.changeOrganization(it) },
            label = { Text("Organization") },
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
private fun AddContactInfoContentPresenter(
    barcodeRepository: BarcodeRepository = rememberInject(),
): AddContactInfoContentStatus {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var organization by remember { mutableStateOf(TextFieldValue("")) }
    val canDone by remember {
        derivedStateOf {
            title.text.isNotEmpty() ||
                name.text.isNotEmpty() ||
                organization.text.isNotEmpty()
        }
    }
    return AddContactInfoContentStatus(
        title = title,
        name = name,
        organization = organization,
        canDone = canDone,
        event = object : AddContactInfoContentEvent {
            override fun changeName(value: TextFieldValue) {
                name = value
            }

            override fun changeOrganization(value: TextFieldValue) {
                organization = value
            }

            override fun changeTitle(value: TextFieldValue) {
                title = value
            }

            override fun done() {
                val type = BarcodeType.ContactInfo(
                    name = BarcodeType.PersonName(
                        formattedName = name.text,
                    ),
                    organization = organization.text,
                    title = title.text,
                    phones = listOf(
                        BarcodeType.Phone(
                            number = "",
                            type = BarcodeType.Phone.Type.UNKNOWN,
                        ),
                    ),
                    emails = listOf(
                        BarcodeType.Email(
                            address = "",
                            subject = "",
                            body = "",
                            type = BarcodeType.Email.Type.UNKNOWN,
                        ),
                    ),
                    urls = listOf(
                        "",
                    ),
                    addresses = listOf(
                        BarcodeType.Address(
                            addressLines = listOf(""),
                            type = BarcodeType.Address.Type.UNKNOWN,
                        ),
                    ),
                )
                barcodeRepository.upset(
                    Barcode(
                        rawValue = type.rawValue,
                        format = BarcodeFormat.FORMAT_2D,
                        type = type,
                    ),
                )
            }
        }
    )
}

private interface AddContactInfoContentEvent {
    fun changeName(value: TextFieldValue)
    fun changeOrganization(value: TextFieldValue)
    fun changeTitle(value: TextFieldValue)
    fun done()
}

private data class AddContactInfoContentStatus(
    val title: TextFieldValue,
    val name: TextFieldValue,
    val organization: TextFieldValue,
    val canDone: Boolean,
    private val event: AddContactInfoContentEvent,
) : AddContactInfoContentEvent by event
