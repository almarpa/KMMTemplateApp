package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onSuccess
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed interface PokemonDetailsUiState {
    data object Loading : PokemonDetailsUiState
    data class Success(val details: PokemonDetails) : PokemonDetailsUiState
    data class Error(val error: AppError) : PokemonDetailsUiState
}

class PokemonDetailsViewModel(
    private val pokemonDetailsUseCase: GetPokemonDetailsUseCase,
) : KmmViewModel() {

    private val _detailsUiState =
        MutableStateFlow<PokemonDetailsUiState>(PokemonDetailsUiState.Loading)
    val detailsUiState: StateFlow<PokemonDetailsUiState> = _detailsUiState

    fun getPokemonDetails(pokemonId: Int) {
        viewModelScope.launch {
            pokemonDetailsUseCase(pokemonId)
                .onSuccess { pokemonDetails ->
                    _detailsUiState.emit(PokemonDetailsUiState.Success(pokemonDetails))
                }.onError { appError ->
                    _detailsUiState.emit(PokemonDetailsUiState.Error(appError))
                }
        }
    }
}
