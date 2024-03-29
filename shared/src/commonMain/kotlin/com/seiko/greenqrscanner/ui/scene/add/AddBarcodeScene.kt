package com.seiko.greenqrscanner.ui.scene.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.scene.add.content.AddCalendarEventContent
import com.seiko.greenqrscanner.ui.scene.add.content.AddContactInfoContent
import com.seiko.greenqrscanner.ui.scene.add.content.AddTextContent
import com.seiko.greenqrscanner.ui.scene.add.content.AddUrlContent
import com.seiko.greenqrscanner.ui.scene.add.content.AddWifiContent
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import io.github.seiko.precompose.annotation.NavGraphDestination
import io.github.seiko.precompose.annotation.Query
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo

@NavGraphDestination(
    route = Route.Add.path,
)
@Composable
fun AddBarcodeSceneRoute(
    navigator: Navigator,
    @Query(Route.paramType) typeString: String?,
) {
    AddBarcodeScene(
        navigator = navigator,
        type = remember {
            typeString?.let {
                AddBarcodeType.valueOf(it)
            } ?: AddBarcodeType.Text
        },
    )
}

@Composable
fun AddBarcodeScene(
    navigator: Navigator,
    type: AddBarcodeType,
) {
    val onDone = remember {
        {
            navigator.navigate(
                Route.Home,
                NavOptions(
                    launchSingleTop = true,
                    popUpTo = PopUpTo.First(false),
                ),
            )
        }
    }
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
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding).fillMaxSize()) {
            when (type) {
                AddBarcodeType.Text -> AddTextContent(
                    onDone = onDone,
                    modifier = Modifier.fillMaxWidth(),
                )
                AddBarcodeType.Url -> AddUrlContent(
                    onDone = onDone,
                    modifier = Modifier.fillMaxWidth(),
                )
                AddBarcodeType.ContactInfo -> AddContactInfoContent(
                    onDone = onDone,
                    modifier = Modifier.fillMaxWidth(),
                )
                AddBarcodeType.Wifi -> AddWifiContent(
                    onDone = onDone,
                    modifier = Modifier.fillMaxWidth(),
                )
                AddBarcodeType.CalendarEvent -> AddCalendarEventContent(
                    onDone = onDone,
                    modifier = Modifier.fillMaxWidth(),
                )
                else -> {
                    Text(
                        "support add type ${type.name}",
                        modifier = Modifier.align(Alignment.Center),
                    )
                }
            }
        }
    }
}
