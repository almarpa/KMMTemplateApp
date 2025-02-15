package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository

class SetDeviceIdUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(deviceId: Long) = this.repository.setDeviceId(deviceId)
}