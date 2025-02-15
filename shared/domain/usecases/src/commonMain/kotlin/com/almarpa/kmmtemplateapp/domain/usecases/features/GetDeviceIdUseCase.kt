package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow

class GetDeviceIdUseCase(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<Long> = this.repository.getDeviceId()
}