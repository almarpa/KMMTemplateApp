package com.almarpa.kmmtemplateapp.domain.usecases.features.preferences

import com.almarpa.kmmtemplateapp.domain.repository.features.preferences.PreferencesRepository

class SetDeviceIdUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(deviceId: Long) = this.repository.setDeviceId(deviceId)
}
