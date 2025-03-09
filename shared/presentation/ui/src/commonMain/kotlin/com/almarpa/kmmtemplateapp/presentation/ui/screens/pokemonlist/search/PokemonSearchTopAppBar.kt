package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.menu_drawer_btn
import kmmtemplateapp.shared.presentation.ui.generated.resources.pokedex_title
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonSearchTopAppBar(
    animatedVisibilityScope: AnimatedVisibilityScope,
    drawerState: DrawerState = DrawerState(DrawerValue.Closed),
    scrollBehaviour: TopAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
    uiState: SearchUiState,
    isSearchActive: Boolean = false,
    onSearchActiveChange: (Boolean) -> Unit = {},
    onDismissSearch: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    onSelected: (Pokemon) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()

    Box {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(Res.string.pokedex_title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu",
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            },
            actions = { SearchIcon { onSearchActiveChange(true) } },
            scrollBehavior = scrollBehaviour,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                scrolledContainerColor = MaterialTheme.colorScheme.surface,
                containerColor = MaterialTheme.colorScheme.surface,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        )
        if (isSearchActive) {
            PokemonSearchBar(
                animatedVisibilityScope = animatedVisibilityScope,
                uiState = uiState,
                onSearch = { onSearch(it) },
                onCancel = {
                    onDismissSearch()
                    onSearchActiveChange(false)
                },
                onSelected = { onSelected(it) }
            )
        }
    }
}

@Composable
fun SearchIcon(onIconClick: () -> Unit) {
    IconButton(onClick = { onIconClick() }) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(Res.string.menu_drawer_btn),
            tint = MaterialTheme.colorScheme.primary,
        )
    }
}