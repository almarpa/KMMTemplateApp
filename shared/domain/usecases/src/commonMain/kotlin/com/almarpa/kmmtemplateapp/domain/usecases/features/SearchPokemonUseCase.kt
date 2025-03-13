package com.almarpa.kmmtemplateapp.domain.usecases.features

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import com.almarpa.kmmtemplateapp.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class SearchPokemonUseCase(private val repository: PokemonRepository) {
    operator fun invoke(name: String): Flow<List<Pokemon>> =
        this.repository.searchPokemonByName(name)
}