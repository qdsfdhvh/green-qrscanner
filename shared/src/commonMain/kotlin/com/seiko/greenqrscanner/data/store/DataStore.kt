package com.seiko.greenqrscanner.data.store

import com.moriatsushi.koject.Provides
import com.moriatsushi.koject.Singleton
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.seiko.greenqrscanner.option.AppCoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Singleton
@Provides
@OptIn(ExperimentalSettingsApi::class)
class DataStoreManager(
    private val settings: FlowSettings,
    private val appCoroutineDispatcher: AppCoroutineDispatcher,
) {

    private val scope by lazy {
        CoroutineScope(appCoroutineDispatcher.io)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> flow(key: String, default: T): Flow<T> {
        return when (default) {
            is String -> settings.getStringFlow(key, default)
            is Double -> settings.getDoubleFlow(key, default)
            is Int -> settings.getIntFlow(key, default)
            is Long -> settings.getLongFlow(key, default)
            is Float -> settings.getFloatFlow(key, default)
            is Boolean -> settings.getBooleanFlow(key, default)
            else -> throw IllegalArgumentException("Unsupported type: $default")
        } as Flow<T>
    }

    fun <T : Any> putAsync(key: String, value: T) {
        scope.launch {
            put(key, value)
        }
    }

    private suspend fun <T : Any> put(key: String, value: T) {
        when (value) {
            is String -> settings.putString(key, value)
            is Double -> settings.putDouble(key, value)
            is Int -> settings.putInt(key, value)
            is Long -> settings.putLong(key, value)
            is Float -> settings.putFloat(key, value)
            is Boolean -> settings.putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported type: $value")
        }
    }
}
