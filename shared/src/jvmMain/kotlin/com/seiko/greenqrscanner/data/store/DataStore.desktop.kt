package com.seiko.greenqrscanner.data.store

import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import com.seiko.greenqrscanner.util.storage.StorageService
import com.seiko.greenqrscanner.util.storage.preferenceDir

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
@Singleton
@Provides
internal fun provideFlowSettings(
    storageService: StorageService
): FlowSettings {
    return DataStoreSettings(
        PreferenceDataStoreFactory.create(
            produceFile = {
                storageService.preferenceDir.resolve("settings.preferences_pb").toFile()
            },
        ),
    )
}
