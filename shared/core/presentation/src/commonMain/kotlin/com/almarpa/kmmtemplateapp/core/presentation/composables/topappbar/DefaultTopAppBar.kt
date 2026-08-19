package com.almarpa.kmmtemplateapp.core.presentation.composables.topappbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.common.platform.isIosPlatform
import com.almarpa.kmmtemplateapp.core.presentation.utils.backButtonImageVector
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    icon: ImageVector = backButtonImageVector(),
    onBackPressed: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { onBackPressed.invoke() }
            ) {
                Icon(
                    modifier = Modifier.padding(start = if (isIosPlatform()) 8.dp else 0.dp),
                    imageVector = icon,
                    contentDescription = "menu_drawer_btn",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState()),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = MaterialTheme.colorScheme.tertiary
        ),
    )
}

@Composable
@Preview()
fun DefaultTopAppBarPreview() {
    DefaultTopAppBar(title = "TopAppBar")
}
