package com.almarpa.kmmtemplateapp.presentation.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

fun MainViewController() = ComposeUIViewController {
    val settingsViewModel = koinViewModel<SettingsViewModel>()
    val userState by settingsViewModel.uiState.collectAsStateWithLifecycle()
    val isDarkTheme = when (val state = userState) {
        is SettingsUiState.Success ->
            when (state.userData.theme) {
                AppThemeEnum.DARK -> true
                AppThemeEnum.LIGHT -> false
                AppThemeEnum.AUTO -> isSystemInDarkTheme()
            }

        else -> true // By default
    }

    AppTheme(isDarkTheme = isDarkTheme) {
        App()
    }
}