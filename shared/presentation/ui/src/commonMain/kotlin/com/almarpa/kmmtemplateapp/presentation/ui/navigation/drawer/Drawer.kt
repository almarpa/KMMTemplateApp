package com.almarpa.kmmtemplateapp.presentation.ui.navigation.drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almarpa.kmmtemplateapp.core.common.platform.getPlatform
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.app_name
import kmmtemplateapp.shared.presentation.ui.generated.resources.settings_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun Drawer(
    navigateToSettings: () -> Unit = {},
    closeDrawer: () -> Unit = {},
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surfaceContainerLow
    ) {
        TemplateAppLogo(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp),
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(Res.string.settings_title)) },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            selected = false,
            onClick = {
                navigateToSettings()
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp, end = 30.dp),
            textAlign = TextAlign.End,
            text = getPlatform().platformData.toString(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun TemplateAppLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(Res.string.app_name),
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}
