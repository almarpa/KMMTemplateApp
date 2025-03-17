package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SearchPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed interface SearchUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data class Success(val pokemonList: List<Pokemon>) : SearchUiState
    data object NotFound : SearchUiState
    data object Error : SearchUiState
}

sealed interface PokemonListUiState {
    data object Loading : PokemonListUiState
    data class Success(val pokemonList: List<Pokemon>) : PokemonListUiState
    data object Error : PokemonListUiState
}

class PokemonListViewModel(
    private val fetchPokemonUseCase: FetchPokemonUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
) : KmmViewModel() {

    private val _pokemonListUiState =
        MutableStateFlow<PokemonListUiState>(PokemonListUiState.Loading)
    val pokemonListUiState: StateFlow<PokemonListUiState> = _pokemonListUiState

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() {
        viewModelScope.launch {
            _pokemonListUiState.value = PokemonListUiState.Loading
            fetchPokemonUseCase()
                .catch {
                    _pokemonListUiState.tryEmit(PokemonListUiState.Error)
                }.collect { pokemonList ->
                    _pokemonListUiState.tryEmit(PokemonListUiState.Success(pokemonList))
                }
        }
    }

    fun onPokemonSearch(name: String) {
        if (name.length > 1) {
            _searchUiState.tryEmit(SearchUiState.Loading)
            viewModelScope.launch {
                searchPokemonUseCase(name)
                    .catch {
                        _searchUiState.tryEmit(SearchUiState.Error)
                    }
                    .collect { searchList ->
                        _searchUiState.tryEmit(
                            if (searchList.isEmpty()) {
                                SearchUiState.NotFound
                            } else {
                                SearchUiState.Success(searchList)
                            }
                        )
                    }
            }
        } else {
            removeCurrentSearch()
        }
    }

    fun removeCurrentSearch() {
        _searchUiState.tryEmit(SearchUiState.Idle)
    }
}
