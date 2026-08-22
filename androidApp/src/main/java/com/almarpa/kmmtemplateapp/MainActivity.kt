package com.almarpa.kmmtemplateapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.App
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val settingsViewModel = koinViewModel<SettingsViewModel>()
            val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()

            val isDarkTheme = when (val state = settingsState) {
                is SettingsUiState.Success ->
                    when (state.theme) {
                        AppThemeEnum.DARK -> true
                        AppThemeEnum.LIGHT -> false
                        AppThemeEnum.AUTO -> isSystemInDarkTheme()
                    }

                else -> isSystemInDarkTheme()
            }

            AppTheme(isDarkTheme = isDarkTheme) {
                App()
            }
        }
    }
}
