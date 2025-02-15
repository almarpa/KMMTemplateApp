package com.almarpa.kmmtemplateapp.domain.repository

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemonList(): Flow<List<Pokemon>>
    suspend fun addPokemon(pokemon: Pokemon)
}