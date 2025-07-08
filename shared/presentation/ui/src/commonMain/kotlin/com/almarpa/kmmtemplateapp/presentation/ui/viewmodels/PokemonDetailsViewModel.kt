package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onSuccess
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.BaseViewModel
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.event.EmptyUiEvent
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.state.BaseUiState
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.usecases.features.AddPokemonToTeamUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonDetailsUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

sealed interface PokemonDetailsUiState : BaseUiState {
    data object Loading : PokemonDetailsUiState
    data class Success(val details: PokemonDetails) : PokemonDetailsUiState
    data class Error(val error: AppError) : PokemonDetailsUiState
}

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val addPokemonToTeamUseCase: AddPokemonToTeamUseCase,
) : BaseViewModel<PokemonDetailsUiState, EmptyUiEvent>(
    initialState = PokemonDetailsUiState.Loading,
) {

    private val loadTrigger = MutableSharedFlow<Int>(replay = 1)

    init {
        loadTrigger
            .onEach { pokemonId ->
                getPokemonDetailsUseCase(pokemonId)
                    .onSuccess { pokemonDetails ->
                        _uiState.emit(PokemonDetailsUiState.Success(pokemonDetails))
                    }.onError { appError ->
                        _uiState.emit(PokemonDetailsUiState.Error(appError))
                    }
            }.launchIn(viewModelScope)
    }

    fun loadDetails(pokemonId: Int) {
        loadTrigger.tryEmit(pokemonId)
    }

    fun addPokemonToTeam(pokemon: Pokemon, isAdded: Boolean) {
        viewModelScope.launch {
            addPokemonToTeamUseCase(pokemon.apply { isTeamMember = isAdded })
        }
    }
}
