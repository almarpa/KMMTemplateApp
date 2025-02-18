package com.almarpa.kmmtemplateapp.domain.repository

import com.almarpa.kmmtemplateapp.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemonList(): Flow<List<Pokemon>>
    fun getTeamMembers(): Flow<List<Pokemon>>
    fun searchPokemonByName(name: String): Flow<List<Pokemon>>
    suspend fun addPokemonToTeam(pokemon: Pokemon)
    suspend fun createPokemon(pokemon: Pokemon)
}