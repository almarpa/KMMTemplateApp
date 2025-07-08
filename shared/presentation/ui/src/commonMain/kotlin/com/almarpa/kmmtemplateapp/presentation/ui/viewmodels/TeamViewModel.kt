package com.almarpa.kmmtemplateapp.presentation.ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.BaseViewModel
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.event.EmptyUiEvent
import com.almarpa.kmmtemplateapp.core.presentation.viewmodels.state.BaseUiState
import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.usecases.features.CreateTeamMemberUseCase
import com.almarpa.kmmtemplateapp.domain.usecases.features.FetchTeamMembersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

sealed interface TeamUiState : BaseUiState {
    data object Loading : TeamUiState
    data class Success(val teamList: List<Pokemon>) : TeamUiState
    data object Error : TeamUiState
}

@OptIn(ExperimentalCoroutinesApi::class)
class TeamViewModel(
    private val fetchTeamMembersUseCase: FetchTeamMembersUseCase,
    private val createTeamMemberUseCase: CreateTeamMemberUseCase
) : BaseViewModel<TeamUiState, EmptyUiEvent>(
    initialState = TeamUiState.Loading,
) {

    private val loadTrigger = MutableSharedFlow<Unit>(replay = 1)

    init {
        loadTrigger
            .onStart { emit(Unit) }
            .flatMapLatest { fetchTeamMembersUseCase() }
            .onStart {
                _uiState.emit(TeamUiState.Loading)
            }
            .onEach { pokemonList ->
                _uiState.emit(TeamUiState.Success(pokemonList))
            }
            .catch {
                _uiState.emit(TeamUiState.Error)
            }
            .launchIn(viewModelScope)
    }

    fun loadData() {
        loadTrigger.tryEmit(Unit)
    }

    fun createPokemonMemberAndReloadTeam(pokemon: Pokemon) {
        viewModelScope.launch {
            createTeamMemberUseCase(pokemon)
            loadData()
        }
    }
}
