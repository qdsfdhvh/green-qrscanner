package com.seiko.greenqrscanner.data.store

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import platform.Foundation.NSUserDefaults

@OptIn(ExperimentalSettingsApi::class)
@Singleton
@Provides
internal fun provideFlowSettings(): FlowSettings {
    return NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults).toFlowSettings()
}
