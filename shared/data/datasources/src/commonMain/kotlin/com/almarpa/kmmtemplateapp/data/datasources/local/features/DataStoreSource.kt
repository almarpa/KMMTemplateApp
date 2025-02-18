package com.almarpa.kmmtemplateapp.data.datasources.local.features

import kotlinx.coroutines.flow.Flow

interface DataStoreSource {
    fun getDeviceId(): Flow<Long>
    suspend fun setDeviceId(deviceId: Long)
}
