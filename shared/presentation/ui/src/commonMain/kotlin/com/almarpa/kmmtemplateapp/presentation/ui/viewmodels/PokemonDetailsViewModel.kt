package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.almarpa.kmmtemplateapp.core.common.errorhandler.entities.AppError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onError
import com.almarpa.kmmtemplateapp.core.common.model.entities.onSuccess
import com.almarpa.kmmtemplateapp.core.ui.utils.serializableType
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.BaseViewModel
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.event.EmptyUiEvent
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.state.BaseUiState
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.models.PokemonDetails
import com.almarpa.kmmtemplateapp.domain.usecases.features.AddPokemonToTeamUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetPokemonDetailsUseCase
import com.almarpa.kmmtemplateapp.presentation.ui.navigation.Routes
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf

sealed interface PokemonDetailsUiState : BaseUiState {
    data object Loading : PokemonDetailsUiState
    data class Success(val details: PokemonDetails) : PokemonDetailsUiState
    data class Error(val error: AppError) : PokemonDetailsUiState
}

class PokemonDetailsViewModel(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val addPokemonToTeamUseCase: AddPokemonToTeamUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<PokemonDetailsUiState, EmptyUiEvent>(
    initialState = PokemonDetailsUiState.Loading,
) {

    private val pokemon: Pokemon = savedStateHandle.toRoute<Routes.Detail>(
        typeMap = mapOf(typeOf<Pokemon>() to serializableType<Pokemon>()),
    ).pokemon
    private val loadTrigger = MutableSharedFlow<Unit>(replay = 1)

    init {
        loadTrigger
            .onStart { emit(Unit) }
            .onEach { pokemonId ->
                getPokemonDetailsUseCase(pokemon.id)
                    .onSuccess { pokemonDetails ->
                        _uiState.emit(PokemonDetailsUiState.Success(pokemonDetails))
                    }.onError { appError ->
                        _uiState.emit(PokemonDetailsUiState.Error(appError))
                    }
            }.launchIn(viewModelScope)
    }

    fun loadDetails() {
        loadTrigger.tryEmit(Unit)
    }

    fun addPokemonToTeam(pokemon: Pokemon, isAdded: Boolean) {
        viewModelScope.launch {
            addPokemonToTeamUseCase(pokemon.apply { isTeamMember = isAdded })
        }
    }
}
