package com.almarpa.kmmtemplateapp.presentation.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.common.model.enums.AppThemeEnum
import com.almarpa.kmmtemplateapp.core.common.model.enums.LocaleEnum
import com.almarpa.kmmtemplateapp.core.presentation.composables.dropdown.CustomDropdown
import com.almarpa.kmmtemplateapp.core.presentation.composables.topappbar.DefaultTopAppBar
import com.almarpa.kmmtemplateapp.core.presentation.theme.AppTheme
import com.almarpa.kmmtemplateapp.core.presentation.theme.LocalThemeIsDark
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SettingsUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode_description
import kmmtemplateapp.shared.presentation.ui.generated.resources.language
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_english
import kmmtemplateapp.shared.presentation.ui.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
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
                    theme = uiState.theme,
                    locales = uiState.locales,
                    selectedLocaleKey = uiState.selectedLocaleKey,
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
    theme: AppThemeEnum,
    locales: Map<String, StringResource>,
    selectedLocaleKey: String,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (Boolean) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        SettingsGroup(title = stringResource(Res.string.language)) {
            LanguagesSection(
                languages = locales,
                currentLanguageKey = selectedLocaleKey,
                onLanguageChange = { onLanguageChange(it) }
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )

        SettingsGroup(title = stringResource(Res.string.dark_mode)) {
            DarkModeSection(
                themeState = theme,
                onChange = { isChecked -> onThemeChange(isChecked) },
            )
        }
    }
}

@Composable
private fun SettingsGroup(
    title: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        content()
    }
}

@Composable
fun LanguagesSection(
    languages: Map<String, StringResource>,
    currentLanguageKey: String,
    onLanguageChange: (String) -> Unit,
) {
    SettingsRow(
        icon = Icons.Default.Language,
        title = stringResource(Res.string.language),
        isVerticalAction = true,
        action = {
            CustomDropdown(
                modifier = Modifier.fillMaxWidth(),
                items = languages.mapValues { item -> stringResource(item.value) },
                selectedKey = currentLanguageKey,
                onClickItem = { selection -> onLanguageChange(selection) })
        }
    )
}

@Composable
fun DarkModeSection(themeState: AppThemeEnum, onChange: (Boolean) -> Unit) {
    val isChecked = when (themeState) {
        AppThemeEnum.DARK -> true
        AppThemeEnum.LIGHT -> false
        AppThemeEnum.AUTO -> LocalThemeIsDark.current
    }
    SettingsRow(
        icon = if (isChecked) Icons.Default.DarkMode else Icons.Default.LightMode,
        title = stringResource(Res.string.dark_mode),
        subtitle = stringResource(Res.string.dark_mode_description),
        action = {
            Switch(
                checked = isChecked,
                onCheckedChange = { onChange(it) },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = MaterialTheme.colorScheme.primary
                ),
                thumbContent = {
                    Icon(
                        imageVector = if (isChecked) {
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
    )
}

@Composable
private fun SettingsRow(
    icon: ImageVector,
    title: String,
    subtitle: String? = null,
    isVerticalAction: Boolean = false,
    action: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 64.dp)
            .padding(16.dp),
        verticalAlignment = if (isVerticalAction) Alignment.Top else Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(8.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(20.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            if (isVerticalAction) {
                Spacer(modifier = Modifier.height(12.dp))
                action()
            }
        }
        if (!isVerticalAction) {
            action()
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    AppTheme {
        SettingsScreen(
            SettingsUiState.Success(
                theme = AppThemeEnum.DARK,
                locales = mapOf(
                    LocaleEnum.EN.value to Res.string.language_english
                ),
                selectedLocaleKey = "en"
            ),
        )
    }
}
