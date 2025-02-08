package com.almarpa.kmmtemplateapp.domain.usecases.features.preferences

import com.almarpa.kmmtemplateapp.domain.repository.features.preferences.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetDeviceIdUseCase(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<Long> = this.repository.getDeviceId()
}