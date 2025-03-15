package com.almarpa.kmmtemplateapp.presentation.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
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
import com.almarpa.kmmtemplateapp.core.ui.utils.setAppLanguage
import com.almarpa.kmmtemplateapp.domain.models.UserData
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode
import kmmtemplateapp.shared.presentation.ui.generated.resources.dark_mode_description
import kmmtemplateapp.shared.presentation.ui.generated.resources.language
import kmmtemplateapp.shared.presentation.ui.generated.resources.language_english
import kmmtemplateapp.shared.presentation.ui.generated.resources.settings_title
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen(
    userData: UserData?,
    locales: Map<String, StringResource>,
    onLanguageChange: (String) -> Unit,
    onThemeChange: (Boolean) -> Unit,
    onBackPressed: () -> Unit,
) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = stringResource(Res.string.settings_title)) {
                onBackPressed()
            }
        }
    ) { paddingValues ->
        userData?.let { userDataNotNull ->
            SettingsContent(
                modifier = Modifier.padding(paddingValues),
                userData = userDataNotNull,
                locales = locales,
                onLanguageChange = { onLanguageChange(it) },
                onThemeChange = { onThemeChange(it) },
            )
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
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .wrapContentSize()
            .fillMaxWidth(),
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(1.dp)
        )
        LanguagesSection(
            languages = locales,
            currentLanguage = locales.getOrElse(userData.locale) { Res.string.language_english },
            onLanguageChange = {
                onLanguageChange(it)
                setAppLanguage(it)
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(1.dp)
        )
        DarkModeSection(
            themeState = userData.theme,
            onChange = { isChecked -> onThemeChange(isChecked) },
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .height(1.dp)
        )
    }
}

@Composable
fun LanguagesSection(
    languages: Map<String, StringResource>,
    currentLanguage: StringResource,
    onLanguageChange: (String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(Res.string.language),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            CustomDropdown(
                items = languages.mapValues { item -> stringResource(item.value) },
                selected = stringResource(currentLanguage),
                onClickItem = { selection -> onLanguageChange(selection) }
            )
        }
    }
}

@Composable
fun DarkModeSection(themeState: AppThemeEnum, onChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(Res.string.dark_mode),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                fontSize = 18.sp
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(Res.string.dark_mode_description),
                style = MaterialTheme.typography.titleSmall,
                maxLines = 3
            )
        }

        Switch(
            checked = themeState == AppThemeEnum.DARK,
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
            }
        )
    }
}
