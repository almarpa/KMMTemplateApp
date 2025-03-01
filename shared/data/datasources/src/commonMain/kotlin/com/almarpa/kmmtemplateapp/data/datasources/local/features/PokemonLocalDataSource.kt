package com.almarpa.kmmtemplateapp.data.datasources.local.features

import com.almarpa.kmmtemplateapp.data.datasources.models.entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

interface PokemonLocalDataSource {
    fun fetchPokemons(): Flow<List<PokemonEntity>>
    fun getTeamMembers(): Flow<List<PokemonEntity>>
    fun searchPokemonByName(name: String): Flow<List<PokemonEntity>>
    suspend fun savePokemons(pokemonList: List<PokemonEntity>)
    suspend fun addPokemonToTeam(pokemon: PokemonEntity)
    suspend fun createPokemon(pokemon: PokemonEntity)
}