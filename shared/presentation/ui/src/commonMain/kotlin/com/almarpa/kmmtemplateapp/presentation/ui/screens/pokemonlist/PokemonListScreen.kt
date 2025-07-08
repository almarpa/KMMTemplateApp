package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.almarpa.kmmtemplateapp.core.presentation.composables.error.ErrorPlaceholderView
import com.almarpa.kmmtemplateapp.core.presentation.composables.loader.FullScreenLoader
import com.almarpa.kmmtemplateapp.core.presentation.previews.AppThemePreview
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.mocks.getPokemonListMock
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.navigationbar.AnimatedBottomAppBar
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.list.PokemonList
import com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search.PokemonSearchTopAppBar
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.PokemonListUiState
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.error_getting_pokemon_list
import kmmtemplateapp.shared.presentation.ui.generated.resources.retry_btn
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(
    ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SharedTransitionScope.PokemonListScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    drawerState: DrawerState,
    currentRoute: Routes,
    searchUiState: SearchUiState,
    pokemonListUiState: PokemonListUiState,
    onReload: () -> Unit,
    onSearch: (text: String) -> Unit,
    onDismissSearch: () -> Unit,
    onPokemonItemClick: (pokemon: Pokemon) -> Unit,
    onBottomBarItemClick: (route: Routes) -> Unit,
) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }
    var isBottomAppBarVisible by rememberSaveable { mutableStateOf(true) }
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val coroutineScope = rememberCoroutineScope()

    BackHandler { /* Do nothing */ }

    LaunchedEffect(Unit) { isBottomAppBarVisible = true }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            PokemonSearchTopAppBar(
                animatedVisibilityScope = animatedVisibilityScope,
                drawerState = drawerState,
                scrollBehaviour = scrollBehaviour,
                uiState = searchUiState,
                isSearchActive = isSearchActive,
                onSearchActiveChange = { isActive ->
                    isSearchActive = isActive
                    isBottomAppBarVisible = !isActive
                },
                onDismissSearch = { onDismissSearch() },
                onSearch = { onSearch(it) },
                onSelected = { pokemon -> onPokemonItemClick(pokemon) }
            )
        },
        content = { paddingValues ->
            PokemonListContent(
                modifier = Modifier.padding(paddingValues = paddingValues),
                animatedVisibilityScope = animatedVisibilityScope,
                pokemonListUiState = pokemonListUiState,
                onReload = { onReload() },
                onNavigateToPokemonDetail = { pokemon ->
                    isBottomAppBarVisible = false
                    onPokemonItemClick(pokemon)
                }
            )
        },
        bottomBar = {
            AnimatedBottomAppBar(
                modifier = Modifier.renderInSharedTransitionScopeOverlay(zIndexInOverlay = 1f),
                isVisible = isBottomAppBarVisible,
                currentRoute = currentRoute,
            ) { newRoute ->
                coroutineScope.launch { drawerState.close() }
                onBottomBarItemClick(newRoute)
            }
        },
    )
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonListContent(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    pokemonListUiState: PokemonListUiState,
    onReload: () -> Unit,
    onNavigateToPokemonDetail: (Pokemon) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        when (pokemonListUiState) {
            is PokemonListUiState.Loading -> {
                FullScreenLoader()
            }

            is PokemonListUiState.Error -> {
                ErrorPlaceholderView(
                    errorDescription = stringResource(Res.string.error_getting_pokemon_list),
                    actionText = stringResource(Res.string.retry_btn)
                ) { onReload() }
            }

            is PokemonListUiState.Success -> {
                PokemonList(
                    animatedVisibilityScope = animatedVisibilityScope,
                    pokemonList = pokemonListUiState.pokemonList,
                    onPokemonItemClick = { onNavigateToPokemonDetail(it) }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
fun PokemonListScreenPreview() {
    AppThemePreview {
        PokemonListScreen(
            animatedVisibilityScope = it,
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.PokemonList,
            searchUiState = SearchUiState.Success(getPokemonListMock()),
            pokemonListUiState = PokemonListUiState.Success(getPokemonListMock()),
            onReload = {},
            onDismissSearch = {},
            onSearch = {},
            onBottomBarItemClick = {},
            onPokemonItemClick = {},
        )
    }
}

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
fun PokemonListScreenWithSearchActivePreview() {
    AppThemePreview {
        PokemonListScreen(
            animatedVisibilityScope = it,
            drawerState = DrawerState(DrawerValue.Closed),
            currentRoute = Routes.PokemonList,
            searchUiState = SearchUiState.Error,
            pokemonListUiState = PokemonListUiState.Success(getPokemonListMock()),
            onReload = {},
            onDismissSearch = {},
            onSearch = {},
            onBottomBarItemClick = {},
            onPokemonItemClick = {},
        )
    }
}