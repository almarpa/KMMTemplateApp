package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum
import com.almarpa.kmmtemplateapp.core.ui.theme.KMMTemplateAppTheme
import com.almarpa.kmmtemplateapp.domain.models.UserData
import com.almarpa.kmmtemplateapp.presentation.ui.screens.settings.SettingsScreen
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_english

@PreviewLightDark
@Composable
fun SettingsScreenPreview() {
    KMMTemplateAppTheme {
        SettingsScreen(
            userData = UserData(
                locale = LocaleEnum.EN.name,
                theme = AppThemeEnum.DARK
            ),
            onThemeChange = {},
            onBackPressed = {},
            locales = mapOf(LocaleEnum.EN.name to Res.string.language_english),
            onLanguageChange = {}
        )
    }
}