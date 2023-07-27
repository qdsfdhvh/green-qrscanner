package com.seiko.greenqrscanner.data.store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings

@OptIn(ExperimentalSettingsApi::class, ExperimentalSettingsImplementation::class)
@Singleton
@Provides
internal fun provideFlowSettings(context: Context): FlowSettings {
    return DataStoreSettings(context.dataStore)
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
