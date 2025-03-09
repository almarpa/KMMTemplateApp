package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetTeamUseCase(private val repository: PokemonRepository) {
    operator fun invoke(): Flow<List<Pokemon>> = this.repository.getTeamMembers()
}