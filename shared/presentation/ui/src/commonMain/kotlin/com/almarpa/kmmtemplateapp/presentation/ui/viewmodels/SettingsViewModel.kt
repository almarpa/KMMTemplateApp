package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.models.UserData
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetUserDataUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppLocaleUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppThemeUseCase
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_english
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_spanish
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

sealed interface SettingsUiState {
    data class Success(val userData: UserData) : SettingsUiState
}

class SettingsViewModel(
    getUserDataUseCase: GetUserDataUseCase,
    private val setAppLocaleUseCase: SetAppLocaleUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
) : KmmViewModel() {

    val userData = getUserDataUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = UserData(locale = LocaleEnum.EN.value, theme = AppThemeEnum.AUTO)
    )

    private val _locales: Map<String, StringResource> = getAppLocales()
    val locales: Map<String, StringResource> = _locales

    fun setAppLocale(newLocale: String) {
        viewModelScope.launch {
            setAppLocaleUseCase(newLocale)
        }
    }

    fun setAppTheme(isChecked: Boolean) {
        viewModelScope.launch {
            setAppThemeUseCase(
                if (isChecked) {
                    AppThemeEnum.DARK.name
                } else {
                    AppThemeEnum.LIGHT.name
                }
            )
        }
    }

    private fun getAppLocales() = mapOf(
        LocaleEnum.EN.value to Res.string.language_english,
        LocaleEnum.ES.value to Res.string.language_spanish
    )
}
