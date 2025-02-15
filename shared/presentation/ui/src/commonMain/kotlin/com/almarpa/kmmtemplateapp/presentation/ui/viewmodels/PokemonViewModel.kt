package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonUseCase: GetPokemonUseCase,
) : KmmViewModel() {

    private val _uiState = MutableStateFlow(0L)
    val uiState: StateFlow<Long> = _uiState

    init {
        getPokemonCount()
    }

    private fun getPokemonCount() {
        viewModelScope.launch {
            getPokemonUseCase().catch {
                _uiState.tryEmit(0)
            }.collect { pokemonList ->
                _uiState.tryEmit(pokemonList.size.toLong())
            }
        }
    }
}