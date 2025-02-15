package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.core.local.preferences.AppPreferences
import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class PreferencesRepositoryImpl(private val preferences: AppPreferences) : PreferencesRepository {
    override fun getDeviceId(): Flow<Long> = this.preferences.getDeviceId()

    override suspend fun setDeviceId(deviceId: Long) = this.preferences.setDeviceId(deviceId)
}