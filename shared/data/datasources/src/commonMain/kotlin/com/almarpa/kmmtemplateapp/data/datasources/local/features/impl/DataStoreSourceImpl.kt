package com.almarpa.kmmtemplateapp.data.datasources.local.features.impl

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.almarpa.kmmtemplateapp.data.datasources.local.features.DataStoreSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreSourceImpl(
    private val dataStore: DataStore<Preferences>
) : DataStoreSource {

    override suspend fun putLong(key: String, value: Long) {
        dataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    override fun getLong(key: String): Flow<Long?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[longPreferencesKey(key)]
            }
    }

    override suspend fun putString(key: String, value: String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    override fun getString(key: String): Flow<String?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[stringPreferencesKey(key)]
            }
    }
}