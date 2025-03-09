package com.almarpa.kmmtemplateapp.presentation.ui.screens.pokemonlist.search

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.presentation.ui.viewmodels.SearchUiState
import kmmtemplateapp.shared.presentation.ui.generated.resources.Res
import kmmtemplateapp.shared.presentation.ui.generated.resources.search_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PokemonSearchBar(
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiState: SearchUiState,
    onSearch: (String) -> Unit,
    onSelected: (Pokemon) -> Unit,
    onCancel: () -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    var isSearchInputLoaded by remember { mutableStateOf(false) }

    SearchBar(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .onGloballyPositioned {
                if (!isSearchInputLoaded) {
                    focusRequester.requestFocus()
                    isSearchInputLoaded = true
                }
            },
        inputField = {
            SearchBarDefaults.InputField(
                query = searchText,
                onQueryChange = {
                    searchText = it
                    onSearch(searchText)
                },
                onSearch = { onSearch(searchText) },
                enabled = true,
                expanded = true,
                onExpandedChange = { },
                placeholder = { Text(text = stringResource(Res.string.search_title)) },
                leadingIcon = null,
                trailingIcon = {
                    IconButton(onClick = { onCancel() }) {
                        Icon(
                            imageVector = Icons.Default.Cancel,
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                },
                interactionSource = null,
            )
        },
        expanded = true,
        onExpandedChange = { },
        shadowElevation = 12.dp,
    ) {
        if (uiState !is SearchUiState.Idle) {
            PokemonSearchBarContent(
                animatedVisibilityScope = animatedVisibilityScope,
                uiState = uiState,
                onSelected = { onSelected(it) }
            )
        }
    }
}