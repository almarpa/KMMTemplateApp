package com.almarpa.kmmtemplateapp.presentation.ui.screens.team

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.DrawerState
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.core.ui.composables.error.GenericRetryView
import com.almarpa.kmmtemplateapp.core.ui.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.ui.composables.topappbar.AnimatedTopAppBar
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.NavigationActions
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar.AnimatedBottomAppBar
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.TeamUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.team_title
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
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
        // TODO: BackHandler { isFabContainerFullScreen = false }
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
                GenericRetryView { onRetry() }
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