package com.almarpa.kmmtemplateapp.data.datasources.local.features.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.almarpa.kmmtemplateapp.data.datasources.local.features.DataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreSourceImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreSource {

    companion object {
        private val DEVICE_ID_KEY = longPreferencesKey("deviceId")
    }

    override fun getDeviceId(): Flow<Long> = dataStore.data.map { preferences ->
        preferences[DEVICE_ID_KEY] ?: 0
    }

    override suspend fun setDeviceId(deviceId: Long) {
        dataStore.edit { preferences ->
            preferences[DEVICE_ID_KEY] = deviceId
        }
    }
}