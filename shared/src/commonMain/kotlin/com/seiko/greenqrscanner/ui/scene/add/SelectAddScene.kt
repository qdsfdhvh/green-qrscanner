package com.seiko.greenqrscanner.ui.scene.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.data.model.AddBarcodeType
import com.seiko.greenqrscanner.data.model.icon
import com.seiko.greenqrscanner.data.model.title
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import io.github.seiko.precompose.annotation.NavGraphDestination
import moe.tlaster.precompose.molecule.producePresenter
import moe.tlaster.precompose.navigation.Navigator

@NavGraphDestination(
    route = Route.SelectAdd,
)
@Composable
fun SelectAddScene(
    navigator: Navigator,
) {
    val status by producePresenter { AddPresenter() }
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    BackButton {
                        navigator.goBack()
                    }
                },
                title = {
                    Text("Select")
                },
            )
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            GridCells.Adaptive(100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.padding(innerPadding).fillMaxWidth(),
        ) {
            items(status.types) { item ->
                AddBarcodeItem(
                    item = item,
                    onClick = {
                        navigator.navigate(Route.Add(item))
                    },
                    modifier = Modifier.size(100.dp),
                )
            }
        }
    }
}

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
            Icon(
                item.icon,
                contentDescription = item.name,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.height(4.dp))
            Text(
                item.title,
                maxLines = 1,
                style = MaterialTheme.typography.labelMedium,
            )
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
