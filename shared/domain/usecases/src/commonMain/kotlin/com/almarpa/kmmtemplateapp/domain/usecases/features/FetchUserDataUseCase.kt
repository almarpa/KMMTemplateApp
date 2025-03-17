package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.domain.models.UserData
import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class FetchUserDataUseCase(private val repository: PreferencesRepository) {
    operator fun invoke(): Flow<UserData> = combine(
        repository.getAppLocale(),
        repository.getAppTheme(),
    ) { locale, theme ->
        UserData(
            locale = locale,
            theme = theme?.let { AppThemeEnum.valueOf(it) } ?: AppThemeEnum.AUTO
        )
    }
}