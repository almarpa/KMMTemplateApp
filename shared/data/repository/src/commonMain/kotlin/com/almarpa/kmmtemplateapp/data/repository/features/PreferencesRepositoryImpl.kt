package com.almarpa.kmmtemplateapp.data.repository.features

import com.almarpa.kmmtemplateapp.data.datasources.local.features.DataStoreSource
import com.almarpa.kmmtemplateapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow


class PreferencesRepositoryImpl(
    private val dataStoreSource: DataStoreSource
) : PreferencesRepository {

    private companion object {
        const val USER_APP_LOCALE = "user_app_locale"
        const val USER_APP_THEME = "user_app_theme"
    }

    override fun getAppLocale(): Flow<String?> =
        dataStoreSource.getString(USER_APP_LOCALE)

    override suspend fun setAppLocale(locale: String) {
        dataStoreSource.putString(USER_APP_LOCALE, locale)
    }

    override fun getAppTheme(): Flow<String?> =
        dataStoreSource.getString(USER_APP_THEME)

    override suspend fun setAppTheme(theme: String) {
        dataStoreSource.putString(USER_APP_THEME, theme)
    }
}
