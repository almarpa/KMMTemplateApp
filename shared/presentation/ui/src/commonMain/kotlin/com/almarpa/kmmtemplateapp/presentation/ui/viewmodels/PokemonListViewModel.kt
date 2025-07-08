package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.BaseViewModel
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.event.EmptyUiEvent
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.state.BaseUiState
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchPokemonUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.SearchPokemonUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed interface SearchUiState : BaseUiState {
    data object Idle : SearchUiState
    data object Loading : SearchUiState
    data class Success(val pokemonList: List<Pokemon>) : SearchUiState
    data object NotFound : SearchUiState
    data object Error : SearchUiState
}

sealed interface PokemonListUiState : BaseUiState {
    data object Loading : PokemonListUiState
    data class Success(val pokemonList: List<Pokemon>) : PokemonListUiState
    data object Error : PokemonListUiState
}

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonListViewModel(
    private val fetchPokemonUseCase: FetchPokemonUseCase,
    private val searchPokemonUseCase: SearchPokemonUseCase,
) : BaseViewModel<PokemonListUiState, EmptyUiEvent>(
    initialState = PokemonListUiState.Loading,
) {

    private val _searchUiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
    val searchUiState: StateFlow<SearchUiState> = _searchUiState

    private val loadTrigger = MutableSharedFlow<Unit>(replay = 1)

    init {
        loadTrigger
            .onStart { loadTrigger.emit(Unit) }
            .flatMapLatest { fetchPokemonUseCase() }
            .onStart {
                _uiState.emit(PokemonListUiState.Loading)
            }
            .onEach { pokemonList ->
                _uiState.emit(PokemonListUiState.Success(pokemonList))
            }
            .catch {
                _uiState.emit(PokemonListUiState.Error)
            }
            .launchIn(viewModelScope)
    }

    fun loadList() {
        loadTrigger.tryEmit(Unit)
    }

    fun onPokemonSearch(name: String) {
        viewModelScope.launch {
            if (name.length > 1) {
                _searchUiState.emit(SearchUiState.Loading)
                searchPokemonUseCase(name)
                    .catch {
                        _searchUiState.emit(SearchUiState.Error)
                    }
                    .collect { searchList ->
                        _searchUiState.emit(
                            if (searchList.isEmpty()) {
                                SearchUiState.NotFound
                            } else {
                                SearchUiState.Success(searchList)
                            }
                        )
                    }
            } else {
                removeCurrentSearch()
            }
        }
    }

    fun removeCurrentSearch() {
        _searchUiState.tryEmit(SearchUiState.Idle)
    }
}
