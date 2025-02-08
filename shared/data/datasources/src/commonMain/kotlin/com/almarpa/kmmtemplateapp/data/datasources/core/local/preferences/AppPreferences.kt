package com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    fun getDeviceId(): Flow<Long>
    suspend fun setDeviceId(deviceId: Long)
}
