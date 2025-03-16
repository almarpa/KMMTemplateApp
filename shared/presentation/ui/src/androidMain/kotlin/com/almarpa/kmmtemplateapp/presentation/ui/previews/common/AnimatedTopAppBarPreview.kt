package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.AnimatedTopAppBar
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.team_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Dark Animated Top App Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkAnimatedTopAppBarPreview() {
    AppTheme {
        AnimatedTopAppBar(
            isVisible = true,
            title = stringResource(Res.string.team_title),
            drawerState = DrawerState(DrawerValue.Closed),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Animated Top App Bar")
@Composable
fun AnimatedTopAppBarPreview() {
    AppTheme {
        AnimatedTopAppBar(
            isVisible = true,
            title = stringResource(Res.string.team_title),
            drawerState = DrawerState(DrawerValue.Closed),
        )
    }
}