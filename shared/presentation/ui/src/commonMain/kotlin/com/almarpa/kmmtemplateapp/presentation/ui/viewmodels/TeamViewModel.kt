package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.ui.viewmodels.KmmViewModel
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.usecases.features.CreateTeamMemberUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.GetTeamUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

sealed interface TeamUiState {
    data object Loading : TeamUiState
    data class Success(val teamList: List<Pokemon>) : TeamUiState
    data object Error : TeamUiState
}

class TeamViewModel(
    private val getTeamUseCase: GetTeamUseCase,
    private val createTeamMemberUseCase: CreateTeamMemberUseCase
) : KmmViewModel() {

    private val _uiState = MutableStateFlow<TeamUiState>(TeamUiState.Loading)
    val uiState: StateFlow<TeamUiState> = _uiState

    init {
        getTeamList()
    }

    fun getTeamList() {
        _uiState.tryEmit(TeamUiState.Loading)
        viewModelScope.launch {
            getTeamUseCase()
                .catch {
                    _uiState.tryEmit(TeamUiState.Error)
                }
                .collect { pokemonList ->
                    _uiState.tryEmit(TeamUiState.Success(pokemonList))
                }
        }
    }

    fun createPokemonMemberAndReload(pokemon: Pokemon) {
        viewModelScope.launch {
            createTeamMemberUseCase(pokemon)
            getTeamList()
        }
    }
}
