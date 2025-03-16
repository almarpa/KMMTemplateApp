package com.almarpa.kmmtemplateapp.presentation.ui.previews.screens.team

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamContent
import com.almarpa.kmmtemplateapp.presentation.ui.screens.team.TeamScreen
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState

@Composable
@Preview("Team Screen")
@Preview(
    "Team Screen Fab Landscape",
    showBackground = true,
    device = "spec:width=400dp,height=900dp,dpi=420,orientation=landscape"
)
@Preview(name = "Team Fab Preview Tablet")
fun TeamScreenFabPreview() {
    AppTheme {
        TeamScreen(
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.Team,
            navigationActions = NavigationActions(rememberNavController()),
            uiState = TeamUiState.Success(getPokemonListMock()),
            onRetry = {},
            onSave = {}
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Team Empty Content Fab", showBackground = true)
@Preview(
    name = "Team Empty Content Fab",
    showBackground = true,
)
fun TeamEmptyContentFabPreview() {
    AppTheme {
        TeamScreen(
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.Team,
            navigationActions = NavigationActions(rememberNavController()),
            uiState = TeamUiState.Success(listOf()),
            onRetry = {},
            onSave = {}
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview("Team Content Fullscreen", showBackground = true)
@Preview(
    name = "Tablet Team Content Fullscreen",
    showBackground = true,
)
@Preview(
    name = "Tablet Portrait Team Content Fullscreen",
    showBackground = true,
    device = "spec:width=1280dp,height=900dp,dpi=420,orientation=portrait"
)
fun TeamContentFullscreenPreview() {
    AppTheme {
        TeamContent(
            paddingValues = PaddingValues(0.dp),
            uiState = TeamUiState.Success(getPokemonListMock()),
            isFabContainerFullscreen = true,
            onRetry = {},
            onFabContainerFullscreenChanged = {},
            onSavePokemon = {}
        )
    }
}
