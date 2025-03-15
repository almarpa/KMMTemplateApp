package com.almarpa.kmmtemplateapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun getAppLocale(): Flow<String?>
    suspend fun setAppLocale(locale: String)
    fun getAppTheme(): Flow<String?>
    suspend fun setAppTheme(theme: String)
}