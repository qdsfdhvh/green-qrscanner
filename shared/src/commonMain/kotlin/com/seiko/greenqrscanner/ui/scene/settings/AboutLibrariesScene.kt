package com.seiko.greenqrscanner.ui.scene.settings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.greenqrscanner.data.model.licenses.LicenseGroup
import com.seiko.greenqrscanner.data.model.licenses.LicenseItem
import com.seiko.greenqrscanner.ui.Route
import com.seiko.greenqrscanner.ui.widget.BackButton
import com.seiko.greenqrscanner.ui.widget.SimpleTopBar
import com.seiko.greenqrscanner.ui.widget.preference.Preference
import com.seiko.greenqrscanner.ui.widget.preference.PreferenceHeader
import com.seiko.greenqrscanner.util.decodeJson
import io.github.seiko.precompose.annotation.Back
import io.github.seiko.precompose.annotation.NavGraphDestination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.readResourceBytes

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
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
        val licenses by produceState(emptyList()) {
            value = withContext(Dispatchers.IO) {
                val json = readResourceBytes(ABOUT_LIBRARIES_JSON_NAME).decodeToString()
                json.decodeJson<List<LicenseItem>>()
                    .groupBy { it.groupId }
                    .map { (groupId, artifacts) ->
                        LicenseGroup(
                            id = groupId,
                            artifacts = artifacts.sortedBy { it.artifactId },
                        )
                    }
                    .sortedBy { it.id }
            }
        }
        LazyColumn(
            Modifier.padding(innerPadding).fillMaxSize(),
        ) {
            licenses.forEach { group ->
                stickyHeader {
                    PreferenceHeader(
                        title = {
                            Text(group.id)
                        },
                        modifier = Modifier.fillMaxSize(),
                        tonalElevation = 1.dp,
                    )
                }

                items(group.artifacts) { artifact ->
                    Preference(
                        title = {
                            Text(artifact.artifactId)
                        },
                        trail = {
                            Text(artifact.version)
                        },
                        summary = {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(4.dp),
                                modifier = Modifier.padding(top = 4.dp),
                            ) {
                                artifact.spdxLicenses?.forEach { license ->
                                    Text(
                                        license.name,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White,
                                        modifier = Modifier
                                            .background(Color.Red, CircleShape)
                                            .padding(horizontal = 6.dp),
                                    )
                                }
                            }
                        },
                    )
                }
            }
        }
    }
}

private const val ABOUT_LIBRARIES_JSON_NAME = "licenses.json"
