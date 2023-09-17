package com.seiko.greenqrscanner.ui.scene.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.util.StableLibs
import com.mikepenz.aboutlibraries.ui.compose.util.stable
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import io.github.seiko.precompose.annotation.Back
import io.github.seiko.precompose.annotation.NavGraphDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource

@OptIn(ExperimentalResourceApi::class)
@NavGraphDestination(
    route = Route.AboutLibraries,
)
@Composable
fun AboutLibrariesScene(
    @Back onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                navigationIcon = {
                    BackButton(onBack)
                },
                title = {
                    Text("About Libraries")
                },
            )
        },
    ) { innerPadding ->
        val libraries by produceState<StableLibs?>(null) {
            value = withContext(Dispatchers.IO) {
                val json = resource(ABOUT_LIBRARIES_JSON_NAME).readBytes().decodeToString()
                Libs.Builder().withJson(json).build().stable
            }
        }
        LibrariesContainer(
            libraries,
            Modifier.padding(innerPadding).fillMaxSize(),
        )
    }
}

private const val ABOUT_LIBRARIES_JSON_NAME = "aboutlibraries.json"
