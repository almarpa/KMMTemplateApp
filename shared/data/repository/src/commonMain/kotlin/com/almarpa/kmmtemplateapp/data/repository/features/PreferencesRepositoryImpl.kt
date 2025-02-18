package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.local.features.DataStoreSource
import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(private val preferences: DataStoreSource) : PreferencesRepository {
    override fun getDeviceId(): Flow<Long> = this.preferences.getDeviceId()

    override suspend fun setDeviceId(deviceId: Long) = this.preferences.setDeviceId(deviceId)
}