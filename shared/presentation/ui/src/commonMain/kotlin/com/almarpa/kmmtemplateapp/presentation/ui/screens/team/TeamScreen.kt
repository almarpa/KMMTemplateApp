package com.almarpa.kmmtemplateapp.presentation.ui.screens.team

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.almarpa.kmmtemplateapp.core.common.platform.isIosPlatform
import com.almarpa.kmmtemplateapp.core.ui.composables.error.ErrorPlaceholderView
import com.almarpa.kmmtemplateapp.core.ui.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.AnimatedTopAppBar
import com.almarpa.kmmtemplateapp.core.ui.theme.AppTheme
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar.AnimatedBottomAppBar
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.empty_string
import kmmtemplateapp.shared.presentation.ui.generated.resources.team_title
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TeamScreen(
    drawerState: DrawerState,
    currentRoute: Routes,
    navigationActions: NavigationActions,
    uiState: TeamUiState,
    onRetry: () -> Unit,
    onSave: (pokemon: Pokemon) -> Unit,
) {
    var isFabContainerFullScreen by rememberSaveable { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    if (isFabContainerFullScreen) {
        BackHandler(isIosPlatform()) { isFabContainerFullScreen = false }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            AnimatedTopAppBar(
                isVisible = !isFabContainerFullScreen,
                drawerState = drawerState,
                title = stringResource(Res.string.team_title),
            )
        },
        content = { paddingValues ->
            TeamContent(
                paddingValues = paddingValues,
                uiState = uiState,
                isFabContainerFullscreen = isFabContainerFullScreen,
                onRetry = { onRetry() },
                onFabContainerFullscreenChanged = { isFabContainerFullScreen = it },
                onSavePokemon = { pokemon ->
                    isFabContainerFullScreen = false
                    onSave(pokemon)
                }
            )
        },
        bottomBar = {
            AnimatedBottomAppBar(
                isVisible = !isFabContainerFullScreen,
                currentRoute = currentRoute,
            ) { onRouteSelected ->
                coroutineScope.launch { drawerState.close() }
                if (onRouteSelected == Routes.PokemonList) {
                    navigationActions.navigateToPokemonList()
                } else {
                    navigationActions.navigateToTeamList()
                }
            }
        }
    )
}

@Composable
fun TeamContent(
    paddingValues: PaddingValues,
    uiState: TeamUiState,
    isFabContainerFullscreen: Boolean,
    onRetry: () -> Unit,
    onFabContainerFullscreenChanged: (Boolean) -> Unit,
    onSavePokemon: (Pokemon) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(if (!isFabContainerFullscreen) paddingValues else PaddingValues(0.dp))
    ) {

        when (uiState) {
            is TeamUiState.Loading -> {
                FullScreenLoader()
            }

            is TeamUiState.Error -> {
                ErrorPlaceholderView(errorDescription = stringResource(Res.string.empty_string)) {
                    onRetry()
                }
            }

            is TeamUiState.Success -> {
                val pagerState = rememberPagerState(pageCount = { uiState.teamList.size })
                val coroutineScope = rememberCoroutineScope()

                TeamPager(pagerState = pagerState, pokemonList = uiState.teamList)
                AnimatedFabContainer(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.BottomEnd),
                    fabContainerState = isFabContainerFullscreen,
                    onFabContainerStateChanged = { onFabContainerFullscreenChanged(it) },
                    onSave = {
                        onSavePokemon(it)
                        coroutineScope.launch {
                            delay(1500)
                            pagerState.animateScrollToPage(uiState.teamList.size)
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview
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
@Preview
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
@Preview
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
