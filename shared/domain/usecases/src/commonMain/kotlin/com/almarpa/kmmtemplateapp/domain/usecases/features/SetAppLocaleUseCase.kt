package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository

class SetAppLocaleUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(locale: String) = repository.setAppLocale(locale)
}