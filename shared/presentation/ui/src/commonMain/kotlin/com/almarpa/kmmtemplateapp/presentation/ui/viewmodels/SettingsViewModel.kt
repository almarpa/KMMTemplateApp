package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum
import com.almarpa.kmmtemplateapp.core.ui.utils.setAppLanguage
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

sealed interface SettingsUiState {
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
) : KmmViewModel() {

    val uiState = fetchUserDataUseCase()
        .map { userData ->
            SettingsUiState.Success(
                userData = userData,
                locales = getAppLocales()
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = SettingsUiState.Loading
        )

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
