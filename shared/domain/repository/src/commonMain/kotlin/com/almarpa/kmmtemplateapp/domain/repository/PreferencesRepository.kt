package com.almarpa.kmmtemplateapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getDeviceId(): Flow<Long>
    suspend fun setDeviceId(deviceId: Long)
}