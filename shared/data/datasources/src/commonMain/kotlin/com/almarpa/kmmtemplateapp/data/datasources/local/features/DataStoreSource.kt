package com.almarpa.kmmtemplateapp.data.datasources.local.features

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
    fun getString(key: String): Flow<String?>
    suspend fun putString(key: String, value: String)
    fun getLong(key: String): Flow<Long?>
    suspend fun putLong(key: String, value: Long)
}
