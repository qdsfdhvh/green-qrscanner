package com.seiko.greenqrscanner.ui.scene.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.ui.icon.rememberBarcode
import com.seiko.greenqrscanner.ui.icon.rememberCalendarMonth
import com.seiko.greenqrscanner.ui.icon.rememberContactMail
import com.seiko.greenqrscanner.ui.icon.rememberContactPhone
import com.seiko.greenqrscanner.ui.icon.rememberContacts
import com.seiko.greenqrscanner.ui.icon.rememberDirectionsCar
import com.seiko.greenqrscanner.ui.icon.rememberLink
import com.seiko.greenqrscanner.ui.icon.rememberMyLocation
import com.seiko.greenqrscanner.ui.icon.rememberShoppingBag
import com.seiko.greenqrscanner.ui.icon.rememberSms
import com.seiko.greenqrscanner.ui.icon.rememberTextAd
import com.seiko.greenqrscanner.ui.icon.rememberUnknownDocument
import com.seiko.greenqrscanner.ui.icon.rememberWifi
import com.seiko.greenqrscanner.ui.widget.BottomSheet
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.Navigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBottomSheet(
    navigator: Navigator,
) {
    val status by producePresenter { AddPresenter() }
    BottomSheet(
        onDismissRequest = { navigator.goBack() },
    ) {
        // Scaffold(
        //     topBar = {
        //         SimpleTopBar(
        //             navigationIcon = {
        //                 BackButton {
        //                     navigator.goBack()
        //                 }
        //             },
        //         )
        //     },
        // ) { innerPadding ->
        LazyVerticalGrid(
            GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            // contentPadding = remember { PaddingValues(8.dp) + innerPadding },
            modifier = Modifier.fillMaxWidth().height(500.dp),
        ) {
            items(status.types) { item ->
                AddBarcodeItem(
                    item = item,
                    onClick = {},
                    modifier = Modifier.size(100.dp),
                )
            }
        }
        // }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddBarcodeItem(
    item: AddBarcodeType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(item.icon, contentDescription = item.name)
            Spacer(Modifier.height(4.dp))
            Text(item.name)
        }
    }
}

@Composable
private fun AddPresenter(): AddStatus {
    val types = remember { AddBarcodeType.values().toList() }
    return AddStatus(
        types = types,
    )
}

private data class AddStatus(
    val types: List<AddBarcodeType>,
)

private enum class AddBarcodeType {
    Text,
    ISBN,
    Product,
    Wifi,
    Url,
    Email,
    Phone,
    Sms,
    Geo,
    ContactInfo,
    DriverLicense,
    CalendarEvent,
    Unknown
}

private val AddBarcodeType.icon: ImageVector
    @Composable
    get() = when (this) {
        AddBarcodeType.Text -> rememberTextAd()
        AddBarcodeType.ISBN -> rememberBarcode()
        AddBarcodeType.Product -> rememberShoppingBag()
        AddBarcodeType.Wifi -> rememberWifi()
        AddBarcodeType.Url -> rememberLink()
        AddBarcodeType.Email -> rememberContactMail()
        AddBarcodeType.Phone -> rememberContactPhone()
        AddBarcodeType.Sms -> rememberSms()
        AddBarcodeType.Geo -> rememberMyLocation()
        AddBarcodeType.ContactInfo -> rememberContacts()
        AddBarcodeType.DriverLicense -> rememberDirectionsCar()
        AddBarcodeType.CalendarEvent -> rememberCalendarMonth()
        AddBarcodeType.Unknown -> rememberUnknownDocument()
    }
