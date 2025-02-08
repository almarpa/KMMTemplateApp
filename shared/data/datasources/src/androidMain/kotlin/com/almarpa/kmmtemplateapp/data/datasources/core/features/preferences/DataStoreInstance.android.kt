package com.almarpa.kmmtemplateapp.data.datasources.features.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.almarpa.kmmtemplateapp.data.datasources.core.local.datastore.createDataStore
import com.almarpa.kmmtemplateapp.data.datasources.core.local.datastore.dataStoreFileName

fun dataStore(context: Context): DataStore<Preferences> =
    createDataStore(
        producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
    )
