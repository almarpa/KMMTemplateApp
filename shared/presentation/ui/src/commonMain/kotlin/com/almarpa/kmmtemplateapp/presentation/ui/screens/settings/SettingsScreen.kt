package com.almarpa.kmmtemplateapp.presentation.ui.screens.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.ui.composables.dropdown.CustomDropdown
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.DefaultTopAppBar
import com.almarpa.kmmtemplateapp.core.ui.utils.getDeviceLocale
import com.almarpa.kmmtemplateapp.domain.models.UserData
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode_description
import kmmtemplateapp.shared.presentation.ui.generated.resources.empty_string
import kmmtemplateapp.shared.presentation.ui.generated.resources.language
import kmmtemplateapp.shared.presentation.ui.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onLanguageChange: (String) -> Unit = {},
    onThemeChange: (Boolean) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    Scaffold(containerColor = MaterialTheme.colorScheme.surface, topBar = {
        DefaultTopAppBar(title = stringResource(Res.string.settings_title)) {
            onBackPressed()
        }
    }) { paddingValues ->
        when (uiState) {
            is SettingsUiState.Success -> {
                SettingsContent(
                    modifier = Modifier.padding(paddingValues),
                    userData = uiState.userData,
                    locales = uiState.locales,
                    onLanguageChange = { onLanguageChange(it) },
                    onThemeChange = { onThemeChange(it) },
                )
            }

            else -> {}
        }
    }
}

@Composable
fun SettingsContent(
    modifier: Modifier,
    userData: UserData,
    locales: Map<String, StringResource>,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (Boolean) -> Unit,
) {
    val currentLocale: StringResource = locales.getOrElse(
        key = userData.locale ?: getDeviceLocale(),
        defaultValue = { Res.string.empty_string }
    )

    Column(
        modifier = modifier.padding(top = 16.dp).wrapContentSize().fillMaxWidth(),
    ) {
        CardItem {
            LanguagesSection(
                languages = locales,
                currentLanguage = stringResource(currentLocale),
                onLanguageChange = { onLanguageChange(it) }
            )
        }

        CardItem {
            DarkModeSection(
                themeState = userData.theme,
                onChange = { isChecked -> onThemeChange(isChecked) },
            )
        }
    }
}

@Composable
private fun CardItem(
    isVisible: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    if (isVisible) {
        Card(
            modifier = modifier.padding(8.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            content()
        }
    }
}

@Composable
fun LanguagesSection(
    languages: Map<String, StringResource>,
    currentLanguage: String,
    onLanguageChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.language),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End
        ) {
            CustomDropdown(
                items = languages.mapValues { item -> stringResource(item.value) },
                selected = currentLanguage,
                onClickItem = { selection -> onLanguageChange(selection) })
        }
    }
}

@Composable
fun DarkModeSection(themeState: AppThemeEnum, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = stringResource(Res.string.dark_mode),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.tertiary,
                maxLines = 1,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                text = stringResource(Res.string.dark_mode_description),
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary,
                maxLines = 3
            )
        }

        Switch(checked = themeState == AppThemeEnum.DARK,
            onCheckedChange = { onChange(it) },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary
            ),
            thumbContent = {
                Icon(
                    imageVector = if (themeState == AppThemeEnum.DARK) {
                        Icons.Filled.DarkMode
                    } else {
                        Icons.Filled.LightMode
                    },
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    contentDescription = null,
                    modifier = Modifier.size(SwitchDefaults.IconSize),
                )
            })
    }
}
