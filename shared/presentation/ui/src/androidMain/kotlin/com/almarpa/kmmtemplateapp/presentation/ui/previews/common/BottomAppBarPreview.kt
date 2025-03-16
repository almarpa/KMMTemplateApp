package com.almarpa.kmmtemplateapp.presentation.ui.previews.common

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getBottomAppBarItemsMock
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar.BottomAppBar


@Composable
@Preview("Bottom App Bar")
@Preview("Bottom App Bar", uiMode = Configuration.UI_MODE_NIGHT_YES)
fun TemplateBottomAppBarPreview() {
    AppTheme {
        BottomAppBar(
            currentRoute = Routes.PokemonList,
            bottomAppBarItems = getBottomAppBarItemsMock(),
            onRouteSelected = {},
        )
    }
}