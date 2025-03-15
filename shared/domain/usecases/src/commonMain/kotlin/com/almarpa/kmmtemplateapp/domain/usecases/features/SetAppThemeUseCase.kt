package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository

class SetAppThemeUseCase(private val repository: PreferencesRepository) {
    suspend operator fun invoke(theme: String) = repository.setAppTheme(theme)
}