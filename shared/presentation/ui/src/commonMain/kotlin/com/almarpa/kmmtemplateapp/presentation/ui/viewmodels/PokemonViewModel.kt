package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed interface PokemonUiState {
    data object Loading : PokemonUiState
    data class Success(val pokemonList: List<Pokemon>) : PokemonUiState
    data class Error(val error: AppError) : PokemonUiState
}

class PokemonViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
) : KmmViewModel() {

    private val _uiState = MutableStateFlow<PokemonUiState>(PokemonUiState.Loading)
    val uiState: StateFlow<PokemonUiState> = _uiState

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            getPokemonUseCase().catch { error ->
                println("a ${error}")
                //_uiState.emit(PokemonUiState.Error(error = e))
            }.collect { pokemonList ->
                _uiState.tryEmit(PokemonUiState.Success(pokemonList))
            }
        }
    }
}