package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum
import com.almarpa.kmmtemplateapp.core.presentation.utils.setAppLanguage
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.BaseViewModel
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.event.EmptyUiEvent
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.state.BaseUiState
import com.almarpa.kmmtemplateapp.domain.models.UserData
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchUserDataUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppLocaleUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SetAppThemeUseCase
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_chinese
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_deutch
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_english
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_french
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_italian
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_portuguese
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_spanish
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

sealed interface SettingsUiState : BaseUiState {
    data object Loading : SettingsUiState
    data class Success(
        val userData: UserData,
        val locales: Map<String, StringResource>,
    ) : SettingsUiState
}

class SettingsViewModel(
    fetchUserDataUseCase: FetchUserDataUseCase,
    private val setAppLocaleUseCase: SetAppLocaleUseCase,
    private val setAppThemeUseCase: SetAppThemeUseCase,
) : BaseViewModel<SettingsUiState, EmptyUiEvent>(
    initialState = SettingsUiState.Loading,
) {

    init {
        fetchUserDataUseCase()
            .map {
                SettingsUiState.Success(
                    userData = it,
                    locales = getAppLocales()
                )
            }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    fun setAppLocale(newLocale: String) {
        viewModelScope.launch {
            setAppLanguage(newLocale)
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
        LocaleEnum.ES.value to Res.string.language_spanish,
        LocaleEnum.PT.value to Res.string.language_portuguese,
        LocaleEnum.IT.value to Res.string.language_italian,
        LocaleEnum.FR.value to Res.string.language_french,
        LocaleEnum.ZH.value to Res.string.language_chinese,
        LocaleEnum.DE.value to Res.string.language_deutch,
    )
}
